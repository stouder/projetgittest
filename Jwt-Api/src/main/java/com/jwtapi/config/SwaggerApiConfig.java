package com.jwtapi.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Spring Boot 3 Jwt API", version = "1.0",
        description = "Authification application", contact = @Contact(name = "Stouder Fabien")),
        security = {@SecurityRequirement(name = "bearerToken"), @SecurityRequirement(name = "cookie")})

        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
        @SecurityScheme(name = "cookie", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, paramName = "cookie")

public class SwaggerApiConfig {
}
