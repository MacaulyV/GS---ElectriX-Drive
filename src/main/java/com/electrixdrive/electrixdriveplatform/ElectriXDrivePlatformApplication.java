package com.electrixdrive.electrixdriveplatform;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "ElectriXDrive Platform API",
                version = "1.0",
                description = "API para a plataforma ElectriXDrive, que permite a comparação e gestão de veículos elétricos e a combustão."
        )
)
@SpringBootApplication
public class ElectriXDrivePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectriXDrivePlatformApplication.class, args);
    }

}
