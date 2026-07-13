package org.ivan.artshow.module.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

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
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name="user_id")
    private Long userId;

    @Column(name="username")
    private String userName;

    @Column(name="password_hash")
    @JsonIgnore
    private String password;

    @Column(name="nickname")
    private String nickName;

    @Column(name="avatar_url")
    private String userAvatar;

    @Column(name="bio")
    private String userBio;

    @Column(name="role")
    private String role;

    @Column(name="created_at")
    private Date createdAt;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
