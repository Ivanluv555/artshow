package org.ivan.artshow.common.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.common.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Authentication Interceptor
 *
 * <p>Implements Spring MVC HandlerInterceptor to intercept requests requiring authentication.
 * Validates JWT token before reaching the Controller and stores user ID in context.</p>
 *
 * <p>Main Features:</p>
 * <ul>
 *   <li>Extract JWT token from request headers</li>
 *   <li>Validate token validity</li>
 *   <li>Parse token and extract user ID</li>
 *   <li>Store user ID in UserContext</li>
 *   <li>Clean up ThreadLocal after request completion</li>
 * </ul>
 *
 * <p>Interceptor Processing Flow:</p>
 * <ol>
 *   <li>Skip OPTIONS preflight requests</li>
 *   <li>Get token from Authorization header</li>
 *   <li>Handle Bearer prefix</li>
 *   <li>Validate and parse token</li>
 *   <li>Store in user context</li>
 *   <li>Clean up context after request</li>
 * </ol>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * Pre-handle method for request interception
     *
     * @param request Current HTTP request
     * @param response Current HTTP response
     * @param handler Handler method object
     * @return true to allow, false to block
     * @throws BizException When token is missing or invalid
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        log.debug("Intercepting request: {} {}", method, requestURI);

        // 1. Check request type
        if ("OPTIONS".equalsIgnoreCase(method)) {
            log.debug("Skipping OPTIONS preflight request");
            return true;
        }

        // 2. Get Token from Authorization header
        String token = request.getHeader("Authorization");

        log.debug("Authorization header: {}", token != null ? token.substring(0, Math.min(20, token.length())) + "..." : "null");

        if (!StringUtils.hasLength(token)) {
            log.warn("Request {} missing Authorization header", requestURI);
            throw new BizException(ResultCodes.NOTLOGIN);
        }

        // 3. Handle "Bearer " prefix if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
            log.debug("Token length after removing Bearer prefix: {}", token.length());
        }

        // 4. Validate and parse token
        try {
            Long userId = JwtUtils.parseToken(token);
            log.debug("Token validated successfully, user ID: {}", userId);

            // 5. Store in context
            UserContext.setUserId(userId);

            return true; // Allow request
        } catch (BizException e) {
            log.error("Token validation failed: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Cleanup method after request completion
     *
     * <p>Executed regardless of request success or failure.
     * Used to clean up user ID in ThreadLocal to prevent memory leaks.</p>
     *
     * @param request Current HTTP request
     * @param response Current HTTP response
     * @param handler Handler method object
     * @param ex Exception object (if any)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 6. Clean up ThreadLocal to prevent memory leaks
        UserContext.remove();
        log.debug("UserContext cleanup completed");
    }
}