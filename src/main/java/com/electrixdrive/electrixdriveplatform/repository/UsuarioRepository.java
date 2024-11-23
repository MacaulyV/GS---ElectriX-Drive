package com.electrixdrive.electrixdriveplatform.repository;

import com.electrixdrive.electrixdriveplatform.model.Usuario; // Certifique-se de que o caminho está correto
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Schema(description = "Repositório para manipulação da entidade Usuario.")
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Schema(description = "Busca um usuário pelo endereço de e-mail", example = "usuario@exemplo.com")
    Usuario findByEmail(String email);
}
