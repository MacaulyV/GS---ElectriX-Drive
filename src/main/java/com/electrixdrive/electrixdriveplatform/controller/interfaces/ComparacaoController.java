package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "ComparacaoController", description = "Controlador responsável pela comparação de veículos.")
public class ComparacaoController {

    @Operation(summary = "Comparação de veículos", description = "Retorna a página de comparação de veículos para o usuário.")
    @GetMapping("/comparacao")
    public String comparacaoVeiculos() {
        return "comparacao"; // Isso deve retornar a página `comparacao.html` da pasta `templates`
    }
}
