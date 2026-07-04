package org.ivan.artshow.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI Configuration
 *
 * <p>Configures Knife4j (enhanced Swagger UI) for API documentation.
 * Provides online API documentation and interface testing with JWT token authentication.</p>
 *
 * <p>Main Features:</p>
 * <ul>
 *   <li>Configure API documentation basic information (title, version, description, etc.)</li>
 *   <li>Configure global JWT authentication parameters</li>
 *   <li>Support direct input of raw token (no need to manually add Bearer prefix)</li>
 * </ul>
 *
 * <p>Access URLs:</p>
 * <ul>
 *   <li>Knife4j UI: http://localhost:8080/doc.html</li>
 *   <li>OpenAPI Docs: http://localhost:8080/v3/api-docs</li>
 * </ul>
 *
 * <p>Usage Instructions:</p>
 * <ol>
 *   <li>Visit /doc.html</li>
 *   <li>Click the "Authorize" button in the top right corner</li>
 *   <li>Paste the token directly in the popup (no need to add "Bearer " prefix)</li>
 *   <li>Click the "Authorize" button to confirm</li>
 *   <li>All subsequent requests will automatically include "Authorization: Bearer {token}" header</li>
 * </ol>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configure OpenAPI documentation
     *
     * <p>Uses HTTP Bearer authentication scheme, users can input raw token in Knife4j interface.
     * The system will automatically format the token as "Bearer {token}" and add it to Authorization header.</p>
     *
     * @return OpenAPI configuration object
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Artshow Backend API Documentation")
                        .version("1.0")
                        .description("Art Exhibition and Course Management System - API for users, courses, orders and all modules\n\n" +
                                "## Authentication Guide\n" +
                                "1. Call `/user/login` endpoint to get JWT token\n" +
                                "2. Click 🔓 button in top right corner for authorization\n" +
                                "3. Paste the token in the input box (no need to add Bearer prefix)\n" +
                                "4. After authorization, all API requests will automatically carry the token")
                        .contact(new Contact().name("Ivan Horn").email("ivan@artshow.com")))
                // Configure global security requirement
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .description("Please enter the JWT token returned by login endpoint (no need to add Bearer prefix)")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}