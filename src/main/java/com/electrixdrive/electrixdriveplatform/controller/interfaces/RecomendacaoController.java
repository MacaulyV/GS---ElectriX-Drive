package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "RecomendacaoController", description = "Controlador responsável por exibir a página de recomendação de veículos.")
public class RecomendacaoController {

    @Operation(summary = "Página de recomendação", description = "Retorna a página de recomendação de veículos para o usuário.")
    @GetMapping("/recomendacao")
    public String mostrarrecomendacao() {
        // Deve retornar o nome do arquivo HTML na pasta resources/templates
        return "recomendacao"; // Certifique-se que está correto e igual ao nome do arquivo
    }
}
