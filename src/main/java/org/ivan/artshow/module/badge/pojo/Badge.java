package org.ivan.artshow.module.badge.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * Badge - 实体类
 *
 * <p>Badge对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "badge")
public class Badge {
    @Id
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name = "badge_id")
    private Long badgeId;

    @Column(name = "name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="icon_url")
    private String iconUrl;

    @Column(name = "created_at")
    private Date createdAt;

    public Badge() {
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
