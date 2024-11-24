package com.electrixdrive.electrixdriveplatform.service;

import com.electrixdrive.electrixdriveplatform.model.VeiculoEletrico;
import com.electrixdrive.electrixdriveplatform.repository.VeiculoEletricoRepository;
import com.electrixdrive.electrixdriveplatform.dto.VeiculoEletricoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(description = "Serviço para manipulação e gerenciamento de veículos elétricos.")
@Service
public class VeiculoEletricoService {

    @Autowired
    private VeiculoEletricoRepository veiculoEletricoRepository;

    @Operation(summary = "Obter todos os veículos elétricos",
            description = "Retorna uma lista de todos os veículos elétricos cadastrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de veículos elétricos obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao obter veículos elétricos")
            })
    public List<VeiculoEletrico> getAllVeiculosEletricos() {
        return veiculoEletricoRepository.findAll();
    }

    @Operation(summary = "Salvar um novo veículo elétrico",
            description = "Salva um novo veículo elétrico no banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Veículo elétrico salvo com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao salvar veículo elétrico"),
                    @ApiResponse(responseCode = "500", description = "Erro interno ao salvar veículo elétrico")
            })
    public VeiculoEletrico salvarVeiculoEletrico(VeiculoEletrico veiculoEletrico) {
        return veiculoEletricoRepository.save(veiculoEletrico);
    }

    @Operation(summary = "Completar dados do veículo elétrico",
            description = "Busca informações adicionais do veículo elétrico pelo modelo e marca utilizando o arquivo JSON.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dados do veículo completados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado no JSON"),
                    @ApiResponse(responseCode = "500", description = "Erro ao ler o arquivo JSON")
            })
    public Optional<VeiculoEletrico> completarDadosVeiculo(String marca, String modelo) {
        try {
            ClassPathResource resource = new ClassPathResource("data/carrosEletricos.json");
            ObjectMapper objectMapper = new ObjectMapper();
            VeiculoEletricoDTO[] veiculos = objectMapper.readValue(resource.getFile(), VeiculoEletricoDTO[].class);

            for (VeiculoEletricoDTO veiculoDTO : veiculos) {
                if (veiculoDTO.getMarca().equalsIgnoreCase(marca)) {
                    for (VeiculoEletricoDTO.Modelo modeloDTO : veiculoDTO.getModelos()) {
                        if (modeloDTO.getModelo().equalsIgnoreCase(modelo)) {
                            VeiculoEletrico veiculoEletrico = new VeiculoEletrico();
                            veiculoEletrico.setMarca(veiculoDTO.getMarca());
                            veiculoEletrico.setModelo(modeloDTO.getModelo());
                            veiculoEletrico.setConsumoMedio(modeloDTO.getConsumoMedioKwh100km());
                            veiculoEletrico.setAutonomia(modeloDTO.getAutonomiaBateriaKm());
                            veiculoEletrico.setCustoRecargaBateria(modeloDTO.getCustoRecargaBateria());
                            veiculoEletrico.setEmissaoCO2(modeloDTO.getEmissoesCO2gKm());
                            return Optional.of(veiculoEletrico);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Operation(summary = "Obter veículos elétricos por marca",
            description = "Busca veículos elétricos pelo nome da marca, utilizando o arquivo JSON.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de veículos da marca especificada obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao ler o arquivo JSON")
            })
    public List<VeiculoEletrico> getVeiculosPorMarca(String marca) {
        try {
            // Lendo o JSON de veículos elétricos
            ClassPathResource resource = new ClassPathResource("data/carrosEletricos.json");
            ObjectMapper objectMapper = new ObjectMapper();
            VeiculoEletricoDTO[] veiculos = objectMapper.readValue(resource.getFile(), VeiculoEletricoDTO[].class);

            // Filtrar veículos que possuem a marca especificada (insensível a maiúsculas e minúsculas)
            return List.of(veiculos).stream()
                    .filter(veiculoDTO -> veiculoDTO.getMarca().equalsIgnoreCase(marca))
                    .flatMap(veiculoDTO -> veiculoDTO.getModelos().stream()
                            .map(modeloDTO -> {
                                VeiculoEletrico veiculo = new VeiculoEletrico();
                                veiculo.setMarca(veiculoDTO.getMarca());
                                veiculo.setModelo(modeloDTO.getModelo());
                                veiculo.setConsumoMedio(modeloDTO.getConsumoMedioKwh100km());
                                veiculo.setAutonomia(modeloDTO.getAutonomiaBateriaKm());
                                veiculo.setCustoRecargaBateria(modeloDTO.getCustoRecargaBateria());
                                veiculo.setEmissaoCO2(modeloDTO.getEmissoesCO2gKm());
                                return veiculo;
                            })
                    )
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Operation(summary = "Obter todas as marcas e modelos disponíveis",
            description = "Busca todas as marcas e modelos únicos dos veículos elétricos cadastrados no banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de marcas e modelos obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao obter marcas e modelos")
            })
    public Set<String> getMarcasModelosDisponiveis() {
        return veiculoEletricoRepository.findAll().stream()
                .map(veiculo -> veiculo.getMarca() + " - " + veiculo.getModelo())
                .collect(Collectors.toSet());
    }

    @Operation(summary = "Deletar todos os veículos elétricos",
            description = "Deleta todos os veículos elétricos do banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Todos os veículos elétricos deletados com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao deletar veículos elétricos")
            })
    public void deletarTodos() {
        System.out.println("Tentando deletar todos os veículos elétricos...");
        veiculoEletricoRepository.deleteAll();
        System.out.println("Deleção concluída.");
    }
}
