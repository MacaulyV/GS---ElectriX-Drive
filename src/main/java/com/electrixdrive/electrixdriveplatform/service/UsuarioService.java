package com.electrixdrive.electrixdriveplatform.service;

import com.electrixdrive.electrixdriveplatform.exception.CredenciaisInvalidasException;
import com.electrixdrive.electrixdriveplatform.model.Usuario;
import com.electrixdrive.electrixdriveplatform.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Schema(description = "Serviço para manipulação e gerenciamento de usuários.")
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Cria um novo usuário",
            description = "Método para criar um novo usuário no banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao criar usuário")
            })
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Autenticar ou registrar um usuário",
            description = "Autentica um usuário existente com base no e-mail e senha ou cria um novo se não existir.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário autenticado ou registrado com sucesso"),
                    @ApiResponse(responseCode = "401", description = "Senha incorreta para o e-mail fornecido"),
                    @ApiResponse(responseCode = "400", description = "Erro ao autenticar ou registrar usuário")
            })
    public Usuario autenticarOuRegistrarUsuario(String email, String senha) {
        // Verifica se o usuário já existe no banco de dados com base no e-mail
        Usuario usuarioExistente = usuarioRepository.findByEmail(email);

        if (usuarioExistente != null) {
            // Se o usuário existe e a senha está correta, retorne o usuário
            if (usuarioExistente.getSenha().equals(senha)) {
                return usuarioExistente;
            } else {
                // Se o e-mail é o mesmo, mas a senha é diferente, lance uma exceção
                throw new CredenciaisInvalidasException("Senha incorreta para o e-mail fornecido.");
            }
        } else {
            // Se o usuário não existe, criar um novo
            Usuario novoUsuario = new Usuario();
            novoUsuario.setEmail(email);
            novoUsuario.setSenha(senha);
            return usuarioRepository.save(novoUsuario);
        }
    }

    @Operation(summary = "Obter todos os usuários",
            description = "Retorna uma lista de todos os usuários cadastrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários obtida com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao obter usuários")
            })
    public List<Usuario> getTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Operation(summary = "Deletar todos os usuários",
            description = "Deleta todos os usuários do banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Todos os usuários deletados com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao deletar usuários")
            })
    public void deleteAllUsuarios() {
        usuarioRepository.deleteAll();
    }
}
