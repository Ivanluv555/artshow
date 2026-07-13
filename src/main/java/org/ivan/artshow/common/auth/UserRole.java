package org.ivan.artshow.common.auth;

/**
 * 用户角色枚举
 *
 * <p>定义系统中的三种用户角色及其权限级别。</p>
 *
 * <p>角色说明：</p>
 * <ul>
 *   <li><strong>USER</strong>: 普通用户 - 可以浏览内容、购买课程/商品、发帖、评论、点赞等</li>
 *   <li><strong>INSTRUCTOR</strong>: 讲师 - 拥有用户的所有权限 + 可以创建和管理自己的课程</li>
 *   <li><strong>ADMIN</strong>: 管理员 - 拥有系统的所有权限，可以管理用户、课程、商品等</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public enum UserRole {
    /**
     * 普通用户
     */
    USER("USER", "普通用户"),

    /**
     * 讲师
     */
    INSTRUCTOR("INSTRUCTOR", "讲师"),

    /**
     * 管理员
     */
    ADMIN("ADMIN", "管理员");

    private final String code;
    private final String description;

    UserRole(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据字符串获取角色枚举
     *
     * @param code 角色代码
     * @return 角色枚举
     * @throws IllegalArgumentException 如果角色代码无效
     */
    public static UserRole fromCode(String code) {
        for (UserRole role : values()) {
            if (role.code.equals(code)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role code: " + code);
    }
}
