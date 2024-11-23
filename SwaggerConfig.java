package com.electrixdrive.electrixdriveplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuração do Swagger para a documentação da API.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Bean de configuração do Swagger.
     * @return Docket configurado para a aplicação.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.electrixdrive.electrixdriveplatform"))
                .paths(PathSelectors.any())
                .build();
    }
}
