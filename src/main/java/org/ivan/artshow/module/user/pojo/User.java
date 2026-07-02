package org.ivan.artshow.module.user.pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ivan.artshow.common.core.result.Result;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体类
 *
 * <p>对应数据库中的user表，存储用户的基本信息。
 * 使用JPA注解映射数据库表结构。</p>
 *
 * <p>主要字段：</p>
 * <ul>
 *   <li>userId: 用户ID，主键，自增</li>
 *   <li>userName: 用户名，用于登录</li>
 *   <li>password: 密码哈希值，序列化时忽略</li>
 *   <li>nickName: 昵称</li>
 *   <li>userAvatar: 头像URL</li>
 *   <li>userBio: 个人简介</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name="user_id")
    private Integer userId;

    @Column(name="username")
    @Getter
    @Setter
    private String userName;

    @Column(name="password_hash")
    @Getter
    @Setter
    @JsonIgnore
    private String password;

    @Column(name="nickname")
    @Getter
    @Setter
    private String nickName;

    @Column(name="avatar_url")
    @Getter
    @Setter
    private String userAvatar;

    @Column(name="bio")
    @Getter
    @Setter
    private String userBio;
}
