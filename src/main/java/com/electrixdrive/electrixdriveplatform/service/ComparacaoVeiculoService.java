package com.electrixdrive.electrixdriveplatform.service;

import com.electrixdrive.electrixdriveplatform.dto.response.ComparacaoVeiculosDTO;
import com.electrixdrive.electrixdriveplatform.model.VeiculoAtual;
import com.electrixdrive.electrixdriveplatform.model.VeiculoEletrico;
import com.electrixdrive.electrixdriveplatform.repository.VeiculoAtualRepository;
import com.electrixdrive.electrixdriveplatform.repository.VeiculoEletricoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Schema(description = "Serviço para realizar a comparação entre veículos a combustão e veículos elétricos.")
@Service
public class ComparacaoVeiculoService {

    @Autowired
    private VeiculoAtualRepository veiculoAtualRepository;

    @Autowired
    private VeiculoEletricoRepository veiculoEletricoRepository;

    @Operation(summary = "Compara veículos a combustão e veículos elétricos",
            description = "Realiza uma comparação entre dois veículos, um a combustão e um elétrico, e calcula métricas como consumo, custos e emissões de CO₂.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Comparação realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Um ou ambos os veículos não foram encontrados no banco de dados"),
                    @ApiResponse(responseCode = "500", description = "Erro interno ao realizar a comparação")
            })
    public ComparacaoVeiculosDTO compararVeiculos(String modeloCombustao, String modeloEletrico) {
        // Buscar veículos no banco de dados
        Optional<VeiculoAtual> veiculoCombustaoOpt = veiculoAtualRepository.findByModelo(modeloCombustao);
        Optional<VeiculoEletrico> veiculoEletricoOpt = veiculoEletricoRepository.findByModelo(modeloEletrico);

        if (veiculoCombustaoOpt.isEmpty() || veiculoEletricoOpt.isEmpty()) {
            throw new IllegalArgumentException("Um ou ambos os veículos não foram encontrados no banco de dados.");
        }

        VeiculoAtual veiculoCombustao = veiculoCombustaoOpt.get();
        VeiculoEletrico veiculoEletrico = veiculoEletricoOpt.get();

        // Calcular consumo e outras métricas
        double consumoCombustivelMensal = veiculoCombustao.getQuilometragemMensal() / veiculoCombustao.getConsumoMedio();
        double consumoEletricoMensal = veiculoCombustao.getQuilometragemMensal() / veiculoEletrico.getConsumoMedio();

        double reabastecimentosNecessarios = Math.ceil(consumoCombustivelMensal / veiculoCombustao.getAutonomia());
        double recargasNecessarias = Math.ceil(consumoEletricoMensal / veiculoEletrico.getAutonomia());

        double emissoesCO2VeiculoAtual = veiculoCombustao.getEmissaoCO2() * veiculoCombustao.getQuilometragemMensal();
        double emissoesCO2VeiculoEletrico = veiculoEletrico.getEmissaoCO2() * veiculoCombustao.getQuilometragemMensal();

        double custoCombustivelMensal = reabastecimentosNecessarios * veiculoCombustao.getCustoCombustivelPorTanque();
        double custoRecargaMensal = recargasNecessarias * veiculoEletrico.getCustoRecargaBateria();

        double economiaMensal = custoCombustivelMensal - custoRecargaMensal;

        // Formatar emissões de CO2 para uma apresentação amigável
        String emissoesCO2FormatadasVeiculoAtual;
        String emissoesCO2FormatadasVeiculoEletrico;

        if (emissoesCO2VeiculoAtual >= 1000) {
            emissoesCO2FormatadasVeiculoAtual = String.format("%.1f kg", emissoesCO2VeiculoAtual / 1000);
        } else {
            emissoesCO2FormatadasVeiculoAtual = String.format("%.0f g", emissoesCO2VeiculoAtual);
        }

        if (emissoesCO2VeiculoEletrico >= 1000) {
            emissoesCO2FormatadasVeiculoEletrico = String.format("%.1f kg", emissoesCO2VeiculoEletrico / 1000);
        } else {
            emissoesCO2FormatadasVeiculoEletrico = String.format("%.0f g", emissoesCO2VeiculoEletrico);
        }

        // Criar o DTO de resposta com os resultados
        ComparacaoVeiculosDTO comparacaoVeiculosDTO = new ComparacaoVeiculosDTO();
        comparacaoVeiculosDTO.setConsumoCombustivelMensal(consumoCombustivelMensal);
        comparacaoVeiculosDTO.setConsumoEletricoMensal(consumoEletricoMensal);
        comparacaoVeiculosDTO.setReabastecimentosNecessarios(reabastecimentosNecessarios);
        comparacaoVeiculosDTO.setRecargasNecessarias(recargasNecessarias);
        comparacaoVeiculosDTO.setEmissoesCO2FormatadasVeiculoAtual(emissoesCO2FormatadasVeiculoAtual);
        comparacaoVeiculosDTO.setEmissoesCO2FormatadasVeiculoEletrico(emissoesCO2FormatadasVeiculoEletrico);
        comparacaoVeiculosDTO.setCustoCombustivelMensal(custoCombustivelMensal);
        comparacaoVeiculosDTO.setCustoRecargaMensal(custoRecargaMensal);
        comparacaoVeiculosDTO.setEconomiaMensal(economiaMensal);

        // Adicionar análise detalhada e conclusão
        comparacaoVeiculosDTO.setAnaliseDetalhada(String.format(
                "Para o seu carro atual (%s), utilizando %s, com uma quilometragem mensal de %.2f km e um consumo médio de %.2f km/l, " +
                        "você precisará de aproximadamente %.2f litros de combustível por mês, o que corresponde a cerca de %.0f reabastecimentos. " +
                        "Para o veículo elétrico (%s), com uma autonomia de %.2f km por carga, você precisará de aproximadamente %.0f recargas de bateria por mês.",
                veiculoCombustao.getModelo(),
                veiculoCombustao.getTipoCombustivel(),
                veiculoCombustao.getQuilometragemMensal(),
                veiculoCombustao.getConsumoMedio(),
                consumoCombustivelMensal,
                reabastecimentosNecessarios,
                veiculoEletrico.getModelo(),
                veiculoEletrico.getAutonomia(),
                recargasNecessarias
        ));

        comparacaoVeiculosDTO.setConclusao(String.format(
                "Embora o custo inicial de aquisição do veículo elétrico possa ser maior, ele se mostra mais eficiente e econômico a longo prazo. " +
                        "Com base nos dados fornecidos, o custo total mensal do seu carro atual será de aproximadamente R$ %.2f, enquanto o custo mensal do veículo elétrico será de aproximadamente R$ %.2f. " +
                        "Com isso, você terá uma economia mensal de R$ %.2f, equivalente a %.2f%% de economia em comparação ao seu carro atual.",
                custoCombustivelMensal,
                custoRecargaMensal,
                economiaMensal,
                (economiaMensal / custoCombustivelMensal) * 100
        ));

        return comparacaoVeiculosDTO;
    }
}
