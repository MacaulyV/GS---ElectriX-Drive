package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "SelecaoVeiculoEletricoController", description = "Controlador responsável por exibir a página de seleção de veículos elétricos.")
public class SelecaoVeiculoEletricoController {

    @Operation(summary = "Página de seleção de veículo elétrico", description = "Retorna a página de seleção de veículos elétricos para o usuário.")
    @GetMapping("/selecaoVeiculoEletrico")
    public String selecaoVeiculoEletrico() {
        return "selecaoVeiculoEletrico"; // Nome do arquivo HTML sem extensão
    }
}
