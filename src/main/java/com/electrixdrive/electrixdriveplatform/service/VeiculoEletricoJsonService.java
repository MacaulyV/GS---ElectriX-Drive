package com.electrixdrive.electrixdriveplatform.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Schema(description = "Serviço para ler e obter os dados dos veículos elétricos a partir do arquivo JSON.")
@Service
public class VeiculoEletricoJsonService {

    @Value("classpath:data/carrosEletricos.json")
    private org.springframework.core.io.Resource carrosEletricosResource;

    @Operation(summary = "Obter veículos elétricos do JSON",
            description = "Lê o arquivo JSON 'carrosEletricos.json' e retorna uma lista de veículos elétricos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Veículos elétricos obtidos com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao ler o arquivo JSON de veículos elétricos")
            })
    public List<Map<String, Object>> getVeiculosEletricos() {
        try {
            InputStream inputStream = carrosEletricosResource.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo carrosEletricos.json", e);
        }
    }
}
