package com.electrixdrive.electrixdriveplatform.repository;

import com.electrixdrive.electrixdriveplatform.model.VeiculoAtual; // Importar corretamente
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Schema(description = "Repositório para manipulação da entidade VeiculoAtual.")
@Repository
public interface VeiculoAtualRepository extends JpaRepository<VeiculoAtual, Long> {

    @Schema(description = "Busca um veículo atual pelo identificador do usuário", example = "1")
    VeiculoAtual findByUsuarioId(Long usuarioId);

    @Schema(description = "Busca um veículo atual pelo modelo ignorando diferenciação de maiúsculas e minúsculas", example = "Corolla")
    Optional<VeiculoAtual> findByModeloIgnoreCase(String modeloCombustao);

    @Schema(description = "Busca um veículo atual pelo modelo", example = "Corolla")
    Optional<VeiculoAtual> findByModelo(String modeloCombustao);
}
