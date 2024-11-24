package com.electrixdrive.electrixdriveplatform.controller;

import com.electrixdrive.electrixdriveplatform.dto.request.VeiculoEletricoRequestDTO;
import com.electrixdrive.electrixdriveplatform.dto.response.VeiculoEletricoRecomendacaoDTO;
import com.electrixdrive.electrixdriveplatform.dto.response.VeiculoEletricoResponseDTO;
import com.electrixdrive.electrixdriveplatform.model.VeiculoEletrico;
import com.electrixdrive.electrixdriveplatform.service.VeiculoEletricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/veiculos-eletricos")
@Tag(name = "VeiculoEletricoController", description = "Controlador responsável pelas operações relacionadas aos veículos elétricos.")
public class VeiculoEletricoController {

    @Autowired
    private VeiculoEletricoService veiculoEletricoService;

    @Operation(summary = "Obter todos os veículos elétricos", description = "Retorna uma lista de todos os veículos elétricos disponíveis.")
    @ApiResponse(responseCode = "200", description = "Lista de veículos elétricos retornada com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    @GetMapping
    public ResponseEntity<List<VeiculoEletricoResponseDTO>> getAllVeiculosEletricos() {
        List<VeiculoEletrico> veiculos = veiculoEletricoService.getAllVeiculosEletricos();

        List<VeiculoEletricoResponseDTO> responseDTOs = veiculos.stream().map(veiculo -> {
            return new VeiculoEletricoResponseDTO(
                    veiculo.getId(),
                    veiculo.getMarca(),
                    veiculo.getModelo(),
                    veiculo.getAno(),
                    veiculo.getConsumoMedio(),
                    veiculo.getAutonomia(),
                    veiculo.getCustoRecargaBateria(),
                    veiculo.getEmissaoCO2(),
                    veiculo.getInteresseSolar()
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

    @Operation(summary = "Salvar um novo veículo elétrico", description = "Salva um novo veículo elétrico baseado nas informações fornecidas.")
    @ApiResponse(responseCode = "200", description = "Veículo salvo com sucesso.")
    @ApiResponse(responseCode = "400", description = "Veículo não encontrado no banco de dados.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    @PostMapping
    public ResponseEntity<?> salvarVeiculoEletrico(@RequestBody VeiculoEletricoRequestDTO veiculoRecebido) {
        Optional<VeiculoEletrico> veiculoCompletoOpt = veiculoEletricoService.completarDadosVeiculo(veiculoRecebido.getMarca(), veiculoRecebido.getModelo());

        if (veiculoCompletoOpt.isPresent()) {
            VeiculoEletrico veiculoCompleto = veiculoCompletoOpt.get();
            veiculoCompleto.setAno(veiculoRecebido.getAno());
            veiculoCompleto.setInteresseSolar(veiculoRecebido.getInteresseSolar());

            VeiculoEletrico veiculoSalvo = veiculoEletricoService.salvarVeiculoEletrico(veiculoCompleto);

            VeiculoEletricoResponseDTO responseDTO = new VeiculoEletricoResponseDTO(
                    veiculoSalvo.getId(),
                    veiculoSalvo.getMarca(),
                    veiculoSalvo.getModelo(),
                    veiculoSalvo.getAno(),
                    veiculoSalvo.getConsumoMedio(),
                    veiculoSalvo.getAutonomia(),
                    veiculoSalvo.getCustoRecargaBateria(),
                    veiculoSalvo.getEmissaoCO2(),
                    veiculoSalvo.getInteresseSolar()
            );

            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.badRequest().body("Veículo não encontrado no banco de dados.");
        }
    }

    @Operation(summary = "Obter marcas e modelos disponíveis", description = "Retorna uma lista das marcas e modelos disponíveis de veículos elétricos.")
    @ApiResponse(responseCode = "200", description = "Lista de marcas e modelos retornada com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    @GetMapping("/marcas-modelos")
    public ResponseEntity<Set<String>> getMarcasModelosDisponiveis() {
        Set<String> marcasModelos = veiculoEletricoService.getMarcasModelosDisponiveis();
        return ResponseEntity.ok(marcasModelos);
    }

    @Operation(summary = "Recomendar veículo elétrico por marca", description = "Recomenda um veículo elétrico baseado na marca fornecida.")
    @ApiResponse(responseCode = "200", description = "Veículo recomendado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Nenhum veículo elétrico encontrado para a marca especificada.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    @GetMapping("/recomendacao/{marca}")
    public ResponseEntity<?> recomendarVeiculoEletrico(@PathVariable String marca) {
        List<VeiculoEletrico> veiculosDaMarca = veiculoEletricoService.getVeiculosPorMarca(marca);

        if (veiculosDaMarca.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum veículo elétrico encontrado para a marca: " + marca);
        }

        Random random = new Random();
        VeiculoEletrico veiculoRecomendado = veiculosDaMarca.get(random.nextInt(veiculosDaMarca.size()));

        VeiculoEletricoRecomendacaoDTO recomendacaoDTO = new VeiculoEletricoRecomendacaoDTO(
                veiculoRecomendado.getMarca(),
                veiculoRecomendado.getModelo(),
                veiculoRecomendado.getConsumoMedio(),
                veiculoRecomendado.getAutonomia(),
                veiculoRecomendado.getCustoRecargaBateria(),
                veiculoRecomendado.getEmissaoCO2()
        );

        return ResponseEntity.ok(recomendacaoDTO);
    }

    @Operation(summary = "Remover todos os veículos elétricos", description = "Remove todos os veículos elétricos registrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Todos os veículos elétricos foram deletados com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro ao tentar deletar os veículos elétricos.")
    @DeleteMapping
    public ResponseEntity<String> deleteAllVeiculosEletricos() {
        try {
            veiculoEletricoService.deletarTodos();
            return ResponseEntity.ok("Todos os veículos elétricos foram deletados com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao tentar deletar os veículos elétricos: " + e.getMessage());
        }
    }
}
