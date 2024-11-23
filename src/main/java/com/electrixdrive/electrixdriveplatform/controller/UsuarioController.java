package com.electrixdrive.electrixdriveplatform.controller;

import com.electrixdrive.electrixdriveplatform.dto.UsuarioDTO;
import com.electrixdrive.electrixdriveplatform.model.Usuario;
import com.electrixdrive.electrixdriveplatform.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "UsuarioController", description = "Controlador responsável pelas operações relacionadas aos usuários.")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Autenticar ou registrar um usuário", description = "Autentica um usuário existente ou cria um novo usuário se ele não estiver registrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário autenticado ou registrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping("/autenticarOuRegistrar")
    public ResponseEntity<Usuario> autenticarOuRegistrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        String email = usuarioDTO.getEmail();
        String senha = usuarioDTO.getSenha();

        Usuario usuario = usuarioService.autenticarOuRegistrarUsuario(email, senha);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @Operation(summary = "Obter todos os usuários", description = "Retorna uma lista de todos os usuários registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Usuario>> getTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.getTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Remover todos os usuários", description = "Remove todos os usuários registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Todos os usuários foram removidos com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsuarios() {
        usuarioService.deleteAllUsuarios();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
