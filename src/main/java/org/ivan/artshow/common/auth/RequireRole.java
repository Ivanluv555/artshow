package org.ivan.artshow.common.auth;

import java.lang.annotation.*;

/**
 * 角色权限注解
 *
 * <p>标记需要特定角色才能访问的接口。支持指定一个或多个角色，
 * 用户只要拥有其中任意一个角色即可访问。</p>
 *
 * <p>使用方式：</p>
 * <ul>
 *   <li>方法级别：标记单个接口需要的角色</li>
 *   <li>类级别：标记整个Controller需要的角色（可被方法级别覆盖）</li>
 * </ul>
 *
 * <p>示例：</p>
 * <pre>
 * {@code
 * // 单个方法，只有管理员可以访问
 * @RequireRole(UserRole.ADMIN)
 * @DeleteMapping("/user")
 * public void deleteUser(@RequestParam Long userId) { ... }
 *
 * // 多个角色，管理员或讲师可以访问
 * @RequireRole({UserRole.ADMIN, UserRole.INSTRUCTOR})
 * @PostMapping("/course")
 * public Result<Course> createCourse(@RequestBody CourseDTO course) { ... }
 *
 * // 整个Controller需要管理员权限
 * @RequireRole(UserRole.ADMIN)
 * @RestController
 * @RequestMapping("/admin")
 * public class AdminController { ... }
 * }
 * </pre>
 *
 * <p><strong>注意：</strong></p>
 * <ul>
 *   <li>如果同时标记了 @Public 和 @RequireRole，@Public 优先级更高（直接放行）</li>
 *   <li>如果方法和类都标记了 @RequireRole，方法级别优先</li>
 *   <li>未标记 @RequireRole 的接口，只要登录即可访问（不检查角色）</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireRole {
    /**
     * 允许访问的角色列表
     *
     * <p>用户只要拥有其中任意一个角色即可访问</p>
     *
     * @return 角色数组
     */
    UserRole[] value();

    /**
     * 权限描述（可选）
     *
     * @return 描述信息
     */
    String description() default "";
}
