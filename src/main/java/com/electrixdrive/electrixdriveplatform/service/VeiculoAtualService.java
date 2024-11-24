package com.electrixdrive.electrixdriveplatform.service;

import com.electrixdrive.electrixdriveplatform.dto.input.VeiculoAtualInputDTO;
import com.electrixdrive.electrixdriveplatform.model.VeiculoAtual;
import com.electrixdrive.electrixdriveplatform.model.VeiculoJson;
import com.electrixdrive.electrixdriveplatform.repository.VeiculoAtualRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Schema(description = "Serviço para manipulação e gerenciamento de veículos atuais.")
@Service
public class VeiculoAtualService {

    @Autowired
    private VeiculoAtualRepository veiculoAtualRepository;

    @Operation(summary = "Obter todos os veículos atuais",
            description = "Retorna uma lista de todos os veículos atuais cadastrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de veículos obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao obter veículos")
            })
    public List<VeiculoAtual> getAllVeiculosAtuais() {
        return veiculoAtualRepository.findAll();
    }

    @Operation(summary = "Deletar todos os veículos atuais",
            description = "Deleta todos os veículos atuais do banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Todos os veículos deletados com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao deletar veículos")
            })
    public void deleteAllVeiculosAtuais() {
        veiculoAtualRepository.deleteAll();
    }

    @Operation(summary = "Obter veículo atual por ID",
            description = "Retorna um veículo atual com base no identificador fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Veículo obtido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro ao obter veículo")
            })
    public Optional<VeiculoAtual> getVeiculoAtualById(Long id) {
        return veiculoAtualRepository.findById(id);
    }

    @Operation(summary = "Salvar um novo veículo atual",
            description = "Salva um novo veículo atual no banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Veículo salvo com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao salvar veículo"),
                    @ApiResponse(responseCode = "500", description = "Erro interno ao salvar veículo")
            })
    public VeiculoAtual salvarVeiculoAtual(VeiculoAtual veiculoAtual) {
        return veiculoAtualRepository.save(veiculoAtual);
    }

    private List<VeiculoJson> veiculosDisponiveis;

    public VeiculoAtualService() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.veiculosDisponiveis = objectMapper.readValue(
                    new File("src/main/resources/data/veiculos.json"),
                    new TypeReference<List<VeiculoJson>>() {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Operation(summary = "Salvar veículo atual a partir de um DTO",
            description = "Salva um veículo atual utilizando os dados de entrada do DTO.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Veículo salvo com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados do veículo inválidos"),
                    @ApiResponse(responseCode = "500", description = "Erro ao salvar veículo")
            })
    public VeiculoAtual salvarVeiculoAtual(VeiculoAtualInputDTO veiculoInputDTO) {
        Optional<VeiculoJson> veiculoValido = validarVeiculo(veiculoInputDTO);

        if (veiculoValido.isEmpty()) {
            throw new IllegalArgumentException("Veículo inválido. Verifique os dados e tente novamente.");
        }

        VeiculoJson veiculoReferenciado = veiculoValido.get();
        VeiculoJson.TipoCombustivel combustivelSelecionado = veiculoReferenciado.getTiposCombustivel().stream()
                .filter(c -> c.getTipo().equalsIgnoreCase(veiculoInputDTO.getTipoCombustivel()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de combustível inválido."));

        VeiculoAtual veiculoParaSalvar = new VeiculoAtual();
        veiculoParaSalvar.setMarca(veiculoInputDTO.getMarca());
        veiculoParaSalvar.setModelo(veiculoInputDTO.getModelo());
        veiculoParaSalvar.setAno(veiculoInputDTO.getAno());
        veiculoParaSalvar.setTipoCombustivel(veiculoInputDTO.getTipoCombustivel());
        veiculoParaSalvar.setQuilometragemMensal(veiculoInputDTO.getQuilometragemMensal());

        veiculoParaSalvar.setConsumoMedio(combustivelSelecionado.getConsumoMedioKmL());
        veiculoParaSalvar.setAutonomia(combustivelSelecionado.getAutonomiaKm());
        veiculoParaSalvar.setEmissaoCO2(combustivelSelecionado.getEmissoesCO2gKm());
        veiculoParaSalvar.setCustoCombustivelPorTanque(combustivelSelecionado.getCustoCombustivelPorTanque());

        return veiculoAtualRepository.save(veiculoParaSalvar);
    }

    private Optional<VeiculoJson> validarVeiculo(VeiculoAtualInputDTO veiculoInput) {
        return veiculosDisponiveis.stream()
                .filter(veiculo ->
                        veiculo.getMarca().equalsIgnoreCase(veiculoInput.getMarca()) &&
                                veiculo.getModelo().equalsIgnoreCase(veiculoInput.getModelo()) &&
                                veiculo.getAnosDisponiveis().contains(veiculoInput.getAno()) &&
                                veiculo.getTiposCombustivel().stream()
                                        .anyMatch(tipo -> tipo.getTipo().equalsIgnoreCase(veiculoInput.getTipoCombustivel()))
                )
                .findFirst();
    }
}
