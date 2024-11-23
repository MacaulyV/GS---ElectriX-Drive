package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "ExploracaoController", description = "Controlador responsável por exibir a tela de introdução 'Explorar'.")
public class ExploracaoController {

    @Operation(summary = "Mostrar tela de exploração", description = "Retorna a página de exploração do veículo para o usuário.")
    @GetMapping("/explorar")
    public String mostrarTelaExploracao() {
        return "veiculoCadastro"; // Nome da página HTML que você deseja exibir, sem a extensão .html
    }
}
