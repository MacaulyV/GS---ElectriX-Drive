package com.electrixdrive.electrixdriveplatform.controller;

import com.electrixdrive.electrixdriveplatform.dto.input.VeiculoAtualInputDTO;
import com.electrixdrive.electrixdriveplatform.model.VeiculoAtual;
import com.electrixdrive.electrixdriveplatform.service.VeiculoAtualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/veiculos-atuais")
@Tag(name = "VeiculoAtualController", description = "Controlador responsável pelas operações relacionadas aos veículos atuais.")
public class VeiculoAtualController {

    @Autowired
    private VeiculoAtualService veiculoAtualService;

    @Operation(summary = "Obter todos os veículos atuais", description = "Retorna uma lista de todos os veículos atuais cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veículos retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping
    public ResponseEntity<List<VeiculoAtual>> getAllVeiculosAtuais() {
        try {
            List<VeiculoAtual> veiculos = veiculoAtualService.getAllVeiculosAtuais();
            return ResponseEntity.ok(veiculos);
        } catch (Exception e) {
            // Log o erro adequadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Remover todos os veículos atuais", description = "Remove todos os veículos atuais cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Todos os veículos foram removidos com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllVeiculosAtuais() {
        try {
            veiculoAtualService.deleteAllVeiculosAtuais();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Log o erro adequadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Salvar um novo veículo atual", description = "Salva um novo veículo atual baseado nas informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo salvo com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro nos dados de entrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping
    public ResponseEntity<VeiculoAtual> salvarVeiculoAtual(@RequestBody VeiculoAtualInputDTO veiculoAtualInputDTO) {
        try {
            VeiculoAtual novoVeiculo = veiculoAtualService.salvarVeiculoAtual(veiculoAtualInputDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // Log o erro adequadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Obter marcas e modelos de veículos atuais", description = "Retorna uma lista com as marcas e modelos dos veículos atuais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de marcas e modelos retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping("/marcas-modelos")
    public ResponseEntity<List<String>> getMarcasModelosVeiculosAtuais() {
        try {
            List<VeiculoAtual> veiculosAtuais = veiculoAtualService.getAllVeiculosAtuais();

            // Transformar em uma lista de strings no formato "Marca - Modelo"
            List<String> marcasModelos = veiculosAtuais.stream()
                    .map(veiculo -> veiculo.getMarca() + " - " + veiculo.getModelo())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(marcasModelos);
        } catch (Exception e) {
            // Log o erro adequadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
