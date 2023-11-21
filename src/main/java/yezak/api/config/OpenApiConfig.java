package yezak.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {

        License license = new License()
                .name("Copyright(C) M-ultiply Corporation All rights reserved.")
                .url("");

        final String securitySchemeName = "bearerAuth";			// bearerAuth, basicAuth
        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");							// bearer, basic
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);

        Info info = new Info()
                .title("예작 API")
                .description("예작 API")
                .version("v1.0.0")
                .license(license);

        return new OpenAPI()
                .components(new Components().addSecuritySchemes(securitySchemeName, securityScheme))
                .addSecurityItem(securityRequirement)
                .info(info);
    }
}
