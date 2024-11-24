package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/data")
@Tag(name = "JsonDataController", description = "Controlador responsável por fornecer dados JSON de veículos.")
public class JsonDataController {

    @Operation(summary = "Obter dados de veículos", description = "Retorna o conteúdo do arquivo JSON contendo dados de veículos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do JSON carregados com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao carregar o arquivo JSON.")
    })
    @GetMapping("/veiculos")
    public ResponseEntity<String> obterDadosVeiculos() {
        try {
            InputStream inputStream = new ClassPathResource("data/veiculos.json").getInputStream();
            String conteudoJson = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));
            return ResponseEntity.ok().body(conteudoJson);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao carregar o JSON: " + e.getMessage());
        }
    }

    @Operation(summary = "Obter dados de veículos elétricos", description = "Retorna o conteúdo do arquivo JSON contendo dados de veículos elétricos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do JSON de veículos elétricos carregados com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao carregar o arquivo JSON de veículos elétricos.")
    })
    @GetMapping("/veiculos-eletricos")
    public ResponseEntity<String> getVeiculosEletricos() {
        try {
            InputStream inputStream = new ClassPathResource("data/carrosEletricos.json").getInputStream();
            String conteudoJson = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));
            return ResponseEntity.ok().body(conteudoJson);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao ler o arquivo JSON: " + e.getMessage());
        }
    }
}
