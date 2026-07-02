package org.ivan.artshow.common.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.common.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 认证拦截器
 *
 * <p>实现Spring MVC的HandlerInterceptor接口，用于拦截需要认证的请求。
 * 在请求到达Controller之前，验证JWT令牌的有效性，并将用户ID存入上下文。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>从请求头中获取JWT令牌</li>
 *   <li>验证令牌的有效性</li>
 *   <li>解析令牌并提取用户ID</li>
 *   <li>将用户ID存入UserContext</li>
 *   <li>请求结束后清理ThreadLocal</li>
 * </ul>
 *
 * <p>拦截器处理流程：</p>
 * <ol>
 *   <li>跳过OPTIONS预检请求</li>
 *   <li>从Authorization请求头获取令牌</li>
 *   <li>处理Bearer前缀</li>
 *   <li>验证并解析令牌</li>
 *   <li>存入用户上下文</li>
 *   <li>请求完成后清理上下文</li>
 * </ol>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 请求处理前的拦截方法
     *
     * @param request 当前HTTP请求
     * @param response 当前HTTP响应
     * @param handler 处理器方法对象
     * @return true表示放行，false表示拦截
     * @throws BizException 当令牌缺失或无效时抛出未登录异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 比对请求类型
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 2. 获取 Token (通常在 Header: Authorization)
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasLength(token)) {
            throw new BizException(ResultCodes.NOTLOGIN);
        }

        // 3. 处理 "Bearer " 前缀（如果前端传了的话）
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 4. 验证并解析
        Integer userId = JwtUtils.parseToken(token);

        // 5. 存入上下文
        UserContext.setUserId(userId);

        return true; // 放行
    }

    /**
     * 请求完成后的清理方法
     *
     * <p>无论请求是否成功，都会执行此方法。
     * 用于清理ThreadLocal中的用户ID，防止内存泄漏。</p>
     *
     * @param request 当前HTTP请求
     * @param response 当前HTTP响应
     * @param handler 处理器方法对象
     * @param ex 异常对象（如果有）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 6. 请求结束，务必清理 ThreadLocal，防止内存泄漏
        UserContext.remove();
    }
}