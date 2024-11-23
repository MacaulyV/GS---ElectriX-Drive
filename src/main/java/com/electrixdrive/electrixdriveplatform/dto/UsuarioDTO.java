package com.electrixdrive.electrixdriveplatform.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para dados de usuário, utilizado para autenticação e registro.")
public class UsuarioDTO {

    @Schema(description = "Endereço de e-mail do usuário", example = "usuario@exemplo.com", required = true)
    private String email;

    @Schema(description = "Senha do usuário", example = "senha123", required = true)
    private String senha;

    // Getters e Setters
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
