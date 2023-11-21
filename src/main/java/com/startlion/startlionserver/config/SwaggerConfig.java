package com.startlion.startlionserver.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "START LION",
                description = "start lion api명세",
                version = "v1"))

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/application/**", "/login/**", "/member/**"}; // 기본 경로

        return GroupedOpenApi.builder()
                .group("START LION API v1")
                .pathsToMatch(paths)
                .build();
    }
}
