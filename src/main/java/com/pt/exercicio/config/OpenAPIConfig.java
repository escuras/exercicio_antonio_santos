package com.pt.exercicio.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "CGD spring boot exercise", description = "Something to add here"))
public class OpenAPIConfig {
}
