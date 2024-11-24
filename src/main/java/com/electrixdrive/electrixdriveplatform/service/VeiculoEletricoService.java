package com.electrixdrive.electrixdriveplatform.service;

import com.electrixdrive.electrixdriveplatform.model.VeiculoEletrico;
import com.electrixdrive.electrixdriveplatform.repository.VeiculoEletricoRepository;
import com.electrixdrive.electrixdriveplatform.dto.VeiculoEletricoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(description = "Serviço para manipulação e gerenciamento de veículos elétricos.")
@Service
public class VeiculoEletricoService {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoEletricoService.class);

    private final VeiculoEletricoRepository veiculoEletricoRepository;
    private List<VeiculoEletricoDTO> veiculosDisponiveis;

    // Injeção via construtor
    public VeiculoEletricoService(VeiculoEletricoRepository veiculoEletricoRepository) {
        this.veiculoEletricoRepository = veiculoEletricoRepository;
        carregarVeiculosDisponiveis();
    }

    private void carregarVeiculosDisponiveis() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("data/carrosEletricos.json");
            InputStream inputStream = resource.getInputStream();
            this.veiculosDisponiveis = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<VeiculoEletricoDTO>>() {}
            );
            logger.info("Veículos disponíveis carregados com sucesso.");
        } catch (IOException e) {
            logger.error("Erro ao carregar carrosEletricos.json: {}", e.getMessage());
            throw new RuntimeException("Não foi possível carregar carrosEletricos.json", e);
        }
    }

    @Operation(summary = "Obter todos os veículos elétricos",
            description = "Retorna uma lista de todos os veículos elétricos cadastrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de veículos elétricos obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao obter veículos elétricos")
            })
    public List<VeiculoEletrico> getAllVeiculosEletricos() {
        try {
            return veiculoEletricoRepository.findAll();
        } catch (Exception e) {
            logger.error("Erro ao obter todos os veículos elétricos: {}", e.getMessage());
            throw e;
        }
    }

    @Operation(summary = "Salvar um novo veículo elétrico",
            description = "Salva um novo veículo elétrico no banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Veículo elétrico salvo com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao salvar veículo elétrico"),
                    @ApiResponse(responseCode = "500", description = "Erro interno ao salvar veículo elétrico")
            })
    public VeiculoEletrico salvarVeiculoEletrico(VeiculoEletrico veiculoEletrico) {
        try {
            return veiculoEletricoRepository.save(veiculoEletrico);
        } catch (Exception e) {
            logger.error("Erro ao salvar veículo elétrico: {}", e.getMessage());
            throw e;
        }
    }

    @Operation(summary = "Completar dados do veículo elétrico",
            description = "Busca informações adicionais do veículo elétrico pelo modelo e marca utilizando o arquivo JSON.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dados do veículo completados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado no JSON"),
                    @ApiResponse(responseCode = "500", description = "Erro ao ler o arquivo JSON")
            })
    public Optional<VeiculoEletrico> completarDadosVeiculo(String marca, String modelo) {
        return veiculosDisponiveis.stream()
                .filter(veiculoDTO -> veiculoDTO.getMarca().equalsIgnoreCase(marca))
                .flatMap(veiculoDTO -> veiculoDTO.getModelos().stream()
                        .filter(modeloDTO -> modeloDTO.getModelo().equalsIgnoreCase(modelo))
                        .map(modeloDTO -> {
                            VeiculoEletrico veiculoEletrico = new VeiculoEletrico();
                            veiculoEletrico.setMarca(veiculoDTO.getMarca());
                            veiculoEletrico.setModelo(modeloDTO.getModelo());
                            veiculoEletrico.setConsumoMedio(modeloDTO.getConsumoMedioKwh100km());
                            veiculoEletrico.setAutonomia(modeloDTO.getAutonomiaBateriaKm());
                            veiculoEletrico.setCustoRecargaBateria(modeloDTO.getCustoRecargaBateria());
                            veiculoEletrico.setEmissaoCO2(modeloDTO.getEmissoesCO2gKm());
                            return veiculoEletrico;
                        })
                )
                .findFirst()
                .map(veiculoEletricoRepository::save)
                .or(() -> {
                    logger.warn("Veículo não encontrado no JSON para marca: {} e modelo: {}", marca, modelo);
                    return Optional.empty();
                });
    }

    @Operation(summary = "Obter veículos elétricos por marca",
            description = "Busca veículos elétricos pelo nome da marca, utilizando o arquivo JSON.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de veículos da marca especificada obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao ler o arquivo JSON")
            })
    public List<VeiculoEletrico> getVeiculosPorMarca(String marca) {
        try {
            return veiculosDisponiveis.stream()
                    .filter(veiculoDTO -> veiculoDTO.getMarca().equalsIgnoreCase(marca))
                    .flatMap(veiculoDTO -> veiculoDTO.getModelos().stream()
                            .map(modeloDTO -> {
                                VeiculoEletrico veiculoEletrico = new VeiculoEletrico();
                                veiculoEletrico.setMarca(veiculoDTO.getMarca());
                                veiculoEletrico.setModelo(modeloDTO.getModelo());
                                veiculoEletrico.setConsumoMedio(modeloDTO.getConsumoMedioKwh100km());
                                veiculoEletrico.setAutonomia(modeloDTO.getAutonomiaBateriaKm());
                                veiculoEletrico.setCustoRecargaBateria(modeloDTO.getCustoRecargaBateria());
                                veiculoEletrico.setEmissaoCO2(modeloDTO.getEmissoesCO2gKm());
                                return veiculoEletrico;
                            })
                    )
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Erro ao obter veículos por marca: {}", e.getMessage());
            throw new RuntimeException("Erro ao obter veículos por marca", e);
        }
    }

    @Operation(summary = "Obter todas as marcas e modelos disponíveis",
            description = "Busca todas as marcas e modelos únicos dos veículos elétricos cadastrados no banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de marcas e modelos obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao obter marcas e modelos")
            })
    public Set<String> getMarcasModelosDisponiveis() {
        try {
            return veiculoEletricoRepository.findAll().stream()
                    .map(veiculo -> veiculo.getMarca() + " - " + veiculo.getModelo())
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            logger.error("Erro ao obter marcas e modelos disponíveis: {}", e.getMessage());
            throw e;
        }
    }

    @Operation(summary = "Deletar todos os veículos elétricos",
            description = "Deleta todos os veículos elétricos do banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Todos os veículos elétricos deletados com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao deletar veículos elétricos")
            })
    public void deletarTodos() {
        try {
            logger.info("Tentando deletar todos os veículos elétricos...");
            veiculoEletricoRepository.deleteAll();
            logger.info("Deleção concluída.");
        } catch (Exception e) {
            logger.error("Erro ao tentar deletar os veículos elétricos: {}", e.getMessage());
            throw e;
        }
    }
}
