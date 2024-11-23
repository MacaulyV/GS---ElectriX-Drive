package com.electrixdrive.electrixdriveplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para redirecionar a página inicial para a interface do Swagger.
 */
@Controller
public class SwaggerHomeRedirect {

    /**
     * Método GET para redirecionar a página inicial ("/") para a documentação Swagger.
     * @return String representando o redirecionamento para o Swagger UI.
     */
    @GetMapping("/")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/";
    }
}
