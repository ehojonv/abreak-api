package com.fiap.abreak_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API ABreak")
                        .version("1.0.0")
                        .description(
                                "API para gerenciamento de pausas saudáveis no trabalho - Global Solution FIAP 2025")
                        .contact(new Contact()
                                .name("Felipe Anselmo - 560661"))
                        .contact(new Contact()
                                .name("João Vinicius Alves - 559369"))
                        .contact(new Contact()
                                .name("Matheus Mariotto - 560276")));
    }
}