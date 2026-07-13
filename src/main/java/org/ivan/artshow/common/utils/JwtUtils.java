package org.ivan.artshow.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JWT工具类
 *
 * <p>提供JWT令牌的生成和解析功能，用于用户身份认证。使用HMAC SHA256算法对令牌进行签名，
 * 确保令牌的安全性和完整性。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>生成JWT令牌：根据用户ID生成包含过期时间的JWT令牌</li>
 *   <li>解析JWT令牌：验证并解析令牌，提取用户ID</li>
 *   <li>令牌有效期管理：默认24小时有效期</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Component
public class JwtUtils {

    // 静态变量保留，但去掉 @Value
    private static String SECRET_STRING;

    // 利用 Spring 的 Setter 注入特性，将配置文件的值注入给静态变量
    @Value("${jwt.secret}")
    public void setSecretString(String secret) {
        JwtUtils.SECRET_STRING = secret;
    }

    // Token 有效期：24小时
    private static final long EXPIRATION = 24 * 60 * 60 * 1000L;

    /**
     * 获取签名密钥
     *
     * @return HMAC SHA256签名密钥
     */
    private static Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_STRING.getBytes());
    }

    /**
     * 生成JWT令牌
     *
     * @param userId 用户ID
     * @return JWT令牌字符串
     */
    public static String createToken(Long userId) {
        return createToken(userId, null);
    }

    /**
     * 生成JWT令牌（包含角色）
     *
     * @param userId 用户ID
     * @param role 用户角色
     * @return JWT令牌字符串
     */
    public static String createToken(Long userId, String role) {
        var builder = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getKey(), SignatureAlgorithm.HS256);

        if (role != null && !role.isEmpty()) {
            builder.claim("role", role);
        }

        return builder.compact();
    }

    /**
     * 解析JWT令牌获取用户ID
     *
     * @param token JWT令牌字符串
     * @return 用户ID
     * @throws BizException 当令牌无效、过期或解析失败时抛出未登录异常
     */
    public static Long parseToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            throw new BizException(ResultCodes.NOTLOGIN);
        }
    }

    /**
     * 解析JWT令牌获取用户角色
     *
     * @param token JWT令牌字符串
     * @return 用户角色，如果令牌中没有角色信息则返回null
     * @throws BizException 当令牌无效、过期或解析失败时抛出未登录异常
     */
    public static String parseRole(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("role", String.class);
        } catch (Exception e) {
            throw new BizException(ResultCodes.NOTLOGIN);
        }
    }

    /**
     * 解析JWT令牌获取所有Claims
     *
     * @param token JWT令牌字符串
     * @return Claims对象
     * @throws BizException 当令牌无效、过期或解析失败时抛出未登录异常
     */
    public static Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new BizException(ResultCodes.NOTLOGIN);
        }
    }
}