package org.ivan.artshow.common.auth;

/**
 * 用户上下文工具类
 *
 * <p>利用ThreadLocal存储当前请求的用户ID和角色，实现线程级别的请求隔离。
 * 在拦截器中解析JWT令牌后，将用户信息存入上下文，使得业务层可以随时获取当前登录用户的信息。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>设置和获取当前请求的用户ID</li>
 *   <li>设置和获取当前请求的用户角色</li>
 *   <li>请求结束时清理ThreadLocal，防止内存泄漏</li>
 * </ul>
 *
 * <p><strong>注意：</strong>必须在请求结束后调用{@link #remove()}方法清理ThreadLocal，
 * 否则可能导致内存泄漏或数据串线。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class UserContext {
    private static final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> roleHolder = new ThreadLocal<>();

    /**
     * 设置当前请求的用户ID
     *
     * @param userId 用户ID
     */
    public static void setUserId(Long userId) {
        userIdHolder.set(userId);
    }

    /**
     * 获取当前请求的用户ID
     *
     * @return 用户ID，如果未设置则返回null
     */
    public static Long getUserId() {
        return userIdHolder.get();
    }

    /**
     * 设置当前请求的用户角色
     *
     * @param role 用户角色
     */
    public static void setRole(String role) {
        roleHolder.set(role);
    }

    /**
     * 获取当前请求的用户角色
     *
     * @return 用户角色，如果未设置则返回null
     */
    public static String getRole() {
        return roleHolder.get();
    }

    /**
     * 检查当前用户是否拥有指定角色
     *
     * @param role 要检查的角色
     * @return 如果用户拥有该角色返回true，否则返回false
     */
    public static boolean hasRole(UserRole role) {
        String currentRole = roleHolder.get();
        return currentRole != null && currentRole.equals(role.getCode());
    }

    /**
     * 检查当前用户是否拥有指定角色中的任意一个
     *
     * @param roles 要检查的角色列表
     * @return 如果用户拥有其中任意一个角色返回true，否则返回false
     */
    public static boolean hasAnyRole(UserRole... roles) {
        String currentRole = roleHolder.get();
        if (currentRole == null) {
            return false;
        }
        for (UserRole role : roles) {
            if (currentRole.equals(role.getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 清理ThreadLocal中的用户信息
     *
     * <p>必须在请求结束时调用此方法，防止内存泄漏。
     * 通常在拦截器的afterCompletion方法中调用。</p>
     */
    public static void remove() {
        userIdHolder.remove();
        roleHolder.remove();
    }
}