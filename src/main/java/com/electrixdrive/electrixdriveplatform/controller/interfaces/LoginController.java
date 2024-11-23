package com.electrixdrive.electrixdriveplatform.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "LoginController", description = "Controlador responsável pela página de login.")
public class LoginController {

    @Operation(summary = "Página de login", description = "Retorna a página de login para o usuário.")
    @GetMapping("/login")
    public String login() {
        return "login"; // Isso fará o Spring Boot procurar o arquivo login.html dentro de /templates/
    }
}
