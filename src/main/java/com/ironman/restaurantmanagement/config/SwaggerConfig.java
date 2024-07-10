package com.ironman.restaurantmanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {

        return new OpenAPI()
                .info(new Info()
                                .title("Rest Api RestaurantManagement")
                                .version("1.0 SNAPSHOT")
                                .description("This is the API documentation for the Restaurant Management system.")
                                .termsOfService("http://swagger.io/terms/")
                        /*.contact(new Contact()
                                .name("John Doe")
                                .url("http://johndoe.com")
                                .email("john.doe@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))*/)
                .addServersItem(new Server().url("http://localhost:8090").description("Development server"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi categoryApi() {
        return GroupedOpenApi.builder()
                .group("categories")
                .pathsToMatch("/categories/**")
                .build();
    }

}
