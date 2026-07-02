package org.ivan.artshow.common.config;

import org.ivan.artshow.common.auth.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 *
 * <p>实现WebMvcConfigurer接口，用于配置Spring MVC的各项特性。
 * 主要包括跨域配置（CORS）和拦截器配置。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>配置跨域资源共享（CORS），允许前端跨域访问API</li>
 *   <li>注册认证拦截器，配置需要拦截和排除的路径</li>
 * </ul>
 *
 * <p>拦截器排除路径：</p>
 * <ul>
 *   <li>/user/login - 用户登录接口</li>
 *   <li>/user/register - 用户注册接口</li>
 *   <li>/doc.html - Swagger文档</li>
 *   <li>/webjars/** - Swagger静态资源</li>
 *   <li>/v3/api-docs/** - Swagger API文档</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    public WebConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    /**
     * 配置跨域资源共享（CORS）
     *
     * @param registry CORS注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 注册拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns(   // 排除不需要登录的接口
                        "/user/login",
                        "/user/register",
                        "/doc.html",
                        "/webjars/**",
                        "/v3/api-docs/**",
                        "/favicon.ico"
                );
    }
}