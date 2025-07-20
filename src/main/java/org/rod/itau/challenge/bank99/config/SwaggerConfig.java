package org.rod.itau.challenge.bank99.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio Itáu Java Júnior API")
                        .version("1.0.0")
                        .description("API que simula transações com regras de negócios específicas e boas práticas de projeto em spring boot")
                );
    }
}
