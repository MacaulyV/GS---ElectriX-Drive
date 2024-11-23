package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import com.electrixdrive.electrixdriveplatform.dto.response.ComparacaoVeiculosDTO;
import com.electrixdrive.electrixdriveplatform.service.ComparacaoVeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comparacao")
@Tag(name = "ComparacaoVeiculoController", description = "Controlador responsável pelas operações de comparação de veículos.")
public class ComparacaoVeiculoController {

    @Autowired
    private ComparacaoVeiculoService comparacaoVeiculoService;

    @Operation(summary = "Comparar veículos", description = "Compara dois modelos de veículos (combustão e elétrico) e retorna os dados de comparação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comparação realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro nos parâmetros fornecidos."),
            @ApiResponse(responseCode = "404", description = "Nenhum veículo encontrado para os modelos fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/comparar")
    public ResponseEntity<?> compararVeiculos(
            @Parameter(description = "Modelo do veículo a combustão para comparação.", required = true)
            @RequestParam String modeloCombustao,

            @Parameter(description = "Modelo do veículo elétrico para comparação.", required = true)
            @RequestParam String modeloEletrico) {
        try {
            // Comparar veículos e verificar se a resposta não é nula
            ComparacaoVeiculosDTO comparacao = comparacaoVeiculoService.compararVeiculos(modeloCombustao, modeloEletrico);
            if (comparacao == null) {
                return ResponseEntity.status(404).body("Nenhum veículo encontrado para os modelos fornecidos.");
            }
            return ResponseEntity.ok(comparacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Tratamento para qualquer outro erro inesperado
            return ResponseEntity.status(500).body("Erro interno do servidor: " + e.getMessage());
        }
    }
}
