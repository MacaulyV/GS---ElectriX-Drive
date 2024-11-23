package com.electrixdrive.electrixdriveplatform.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(description = "Entidade que representa o usuário do sistema.")
@Entity
public class Usuario {

    @Schema(description = "Identificador único do usuário", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Endereço de e-mail do usuário", example = "usuario@exemplo.com", required = true)
    private String email;

    @Schema(description = "Senha do usuário", example = "senha123", required = true)
    private String senha;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
