package org.ivan.artshow.common.auth;

/**
 * 用户上下文工具类
 *
 * <p>利用ThreadLocal存储当前请求的用户ID，实现线程级别的请求隔离。
 * 在拦截器中解析JWT令牌后，将用户ID存入上下文，使得业务层可以随时获取当前登录用户的ID。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>设置当前请求的用户ID</li>
 *   <li>获取当前请求的用户ID</li>
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
    private static final ThreadLocal<Long> userHolder = new ThreadLocal<>();

    /**
     * 设置当前请求的用户ID
     *
     * @param userId 用户ID
     */
    public static void setUserId(Long userId) {
        userHolder.set(userId);
    }

    /**
     * 获取当前请求的用户ID
     *
     * @return 用户ID，如果未设置则返回null
     */
    public static Long getUserId() {
        return userHolder.get();
    }

    /**
     * 清理ThreadLocal中的用户ID
     *
     * <p>必须在请求结束时调用此方法，防止内存泄漏。
     * 通常在拦截器的afterCompletion方法中调用。</p>
     */
    public static void remove() {
        userHolder.remove();
    }
}