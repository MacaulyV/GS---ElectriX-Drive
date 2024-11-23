package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "EscolhaController", description = "Controlador responsável por retornar a página de escolha.")
public class EscolhaController {

    @Operation(summary = "Página de escolha", description = "Retorna a página de escolha para o usuário.")
    @GetMapping("/escolha")
    public String escolhaPage() {
        return "escolha"; // Nome do arquivo HTML (sem a extensão)
    }
}
