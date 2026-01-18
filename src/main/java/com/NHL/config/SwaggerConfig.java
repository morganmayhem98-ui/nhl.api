package com.NHL.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "NHL API",
        version = "1.0",
        description = "Spring Boot REST API for Hockey League"
    )
)
public class SwaggerConfig {}
