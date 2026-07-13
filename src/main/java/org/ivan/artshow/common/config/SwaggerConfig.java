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
     * <p>Uses HTTP Bearer authentication scheme with JWT token containing user role information.
     * The system will automatically format the token as "Bearer {token}" and add it to Authorization header.</p>
     *
     * @return OpenAPI configuration object
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Artshow Backend API Documentation")
                        .version("2.1.0")
                        .description("Art Exhibition and Course Management System - API Documentation\n\n" +
                                "## Authentication & Authorization\n\n" +
                                "This API uses **JWT Bearer Token** authentication with **Role-Based Access Control (RBAC)**.\n\n" +
                                "### User Roles\n" +
                                "- **USER**: Regular users - Can browse, purchase, post, comment, like\n" +
                                "- **INSTRUCTOR**: Instructors - USER permissions + Create/manage courses and products\n" +
                                "- **ADMIN**: Administrators - Full system access including user management\n\n" +
                                "### How to Use\n" +
                                "1. **Register**: Call `POST /user` to create an account (default role: USER)\n" +
                                "2. **Login**: Call `POST /user/login` to get your JWT token (contains userId and role)\n" +
                                "3. **Authorize**: Click the 🔓 **Authorize** button in the top right corner\n" +
                                "4. **Enter Token**: Paste your JWT token (no need to add 'Bearer' prefix)\n" +
                                "5. **Test APIs**: All subsequent requests will automatically include your token\n\n" +
                                "### API Access Levels\n" +
                                "- 🌐 **Public**: No authentication required (marked with green lock)\n" +
                                "- 🔒 **Login Required**: Any authenticated user\n" +
                                "- 👨‍🏫 **Instructor/Admin**: INSTRUCTOR or ADMIN role required\n" +
                                "- 🛡️ **Admin Only**: ADMIN role required\n\n" +
                                "### Test Accounts\n" +
                                "```sql\n" +
                                "-- Admin account\n" +
                                "username: admin\n" +
                                "password: admin123\n" +
                                "role: ADMIN\n\n" +
                                "-- Instructor account\n" +
                                "username: instructor\n" +
                                "password: instructor123\n" +
                                "role: INSTRUCTOR\n" +
                                "```\n\n" +
                                "### Permission Denied?\n" +
                                "If you receive **403 Forbidden**, your role doesn't have sufficient permissions for this operation.")
                        .contact(new Contact()
                                .name("Ivan Horn")
                                .email("ivan@artshow.com")))
                // Configure global security requirement
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .description("JWT Bearer Token - Contains user ID and role information\n\n" +
                                                "**How to get token:**\n" +
                                                "1. Use `POST /user/login` endpoint with your credentials\n" +
                                                "2. Copy the token from the response\n" +
                                                "3. Paste it here (no need to add 'Bearer' prefix)\n\n" +
                                                "**Token Structure:**\n" +
                                                "```json\n" +
                                                "{\n" +
                                                "  \"sub\": \"userId\",\n" +
                                                "  \"role\": \"USER|INSTRUCTOR|ADMIN\",\n" +
                                                "  \"iat\": \"issued at timestamp\",\n" +
                                                "  \"exp\": \"expiration timestamp (24 hours)\"\n" +
                                                "}\n" +
                                                "```\n\n" +
                                                "**Token will be sent as:** `Authorization: Bearer {your-token}`")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}