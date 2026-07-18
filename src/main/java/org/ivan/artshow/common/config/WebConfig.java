package org.ivan.artshow.common.config;

import org.ivan.artshow.common.auth.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
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
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 注册拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        final AuthInterceptor authInterceptor2 = authInterceptor;
        if (authInterceptor2 != null) {
            registry.addInterceptor(authInterceptor2)
                    .addPathPatterns("/**") // Intercept all paths
                    .excludePathPatterns(   // Exclude paths that don't require authentication
                            "/user/login",
                            "/user/register",
                            "/v3/api-docs/**",
                            "/v3/api-docs",
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/swagger-resources/**",
                            "/webjars/**",
                            "/favicon.ico",
                            "/error"
                    );
        } else {
            // TODO handle null value
        }
    }
}