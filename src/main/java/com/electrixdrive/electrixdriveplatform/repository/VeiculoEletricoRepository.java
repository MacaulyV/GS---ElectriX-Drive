package com.electrixdrive.electrixdriveplatform.repository;

import com.electrixdrive.electrixdriveplatform.model.VeiculoEletrico;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Schema(description = "Repositório para manipulação da entidade VeiculoEletrico.")
public interface VeiculoEletricoRepository extends JpaRepository<VeiculoEletrico, Long> {

    @Schema(description = "Busca um veículo elétrico pela marca e modelo", example = "Tesla, Model S")
    Optional<VeiculoEletrico> findByMarcaAndModelo(String marca, String modelo);

    @Schema(description = "Busca um veículo elétrico pelo modelo ignorando diferenciação de maiúsculas e minúsculas", example = "Model S")
    Optional<VeiculoEletrico> findByModeloIgnoreCase(String modeloEletrico);

    @Schema(description = "Busca um veículo elétrico pelo modelo", example = "Model S")
    Optional<VeiculoEletrico> findByModelo(String modeloEletrico);
}
