package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "IntroductionController", description = "Controlador responsável por exibir a página de introdução.")
public class IntroductionController {

    @Operation(summary = "Página de introdução", description = "Retorna a página de introdução para o usuário.")
    @GetMapping("/introducao")
    public String showIntroductionPage() {
        return "introduction"; // Deve corresponder ao nome do arquivo HTML (introducao.html) na pasta templates
    }
}
