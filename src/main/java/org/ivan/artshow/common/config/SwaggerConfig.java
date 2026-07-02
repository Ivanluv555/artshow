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
 * Swagger/OpenAPI配置类
 *
 * <p>配置Knife4j（增强版Swagger UI）的API文档功能。
 * 提供在线API文档和接口测试功能，支持JWT令牌认证。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>配置API文档的基本信息（标题、版本、描述等）</li>
 *   <li>配置全局JWT认证参数</li>
 *   <li>启用Bearer Token认证方案</li>
 * </ul>
 *
 * <p>访问地址：</p>
 * <ul>
 *   <li>Knife4j UI: http://localhost:8080/doc.html</li>
 *   <li>OpenAPI文档: http://localhost:8080/v3/api-docs</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Configuration
public class SwaggerConfig {

    /**
     * 配置OpenAPI文档
     *
     * @return OpenAPI配置对象
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Artshow 后端接口文档")
                        .version("1.0")
                        .description("艺术展览与课程管理系统 - 包含用户、课程、订单等所有模块的API")
                        .contact(new Contact().name("Ivan Horn")))
                // 配置全局鉴权参数
                .addSecurityItem(new SecurityRequirement().addList("Bearer Token"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Token",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")));
    }
}