package org.ivan.artshow.common.auth;

import java.lang.annotation.*;

/**
 * 公开接口注解
 *
 * <p>标记此注解的接口不需要JWT鉴权，可以公开访问。</p>
 *
 * <p>使用方式：</p>
 * <ul>
 *   <li>方法级别：标记单个接口为公开接口</li>
 *   <li>类级别：标记整个Controller的所有接口为公开接口</li>
 * </ul>
 *
 * <p>示例：</p>
 * <pre>
 * {@code
 * // 单个方法公开
 * @Public("用户登录")
 * @PostMapping("/login")
 * public Result<String> login() { ... }
 *
 * // 整个Controller公开
 * @Public
 * @RestController
 * public class PublicController { ... }
 * }
 * </pre>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Public {
    /**
     * 接口描述（可选）
     * @return 描述信息
     */
    String value() default "";
}
