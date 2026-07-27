package org.ivan.artshow.module.artcategory.pojo;

import jakarta.persistence.*;
import org.ivan.artshow.common.config.SnowflakeId;

import java.util.Date;

/**
 * Artcategory - 实体类
 *
 * <p>Artcategory对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "art_category")
public class Artcategory {
    @Id
    @SnowflakeId
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "created_at")
    private Date createdAt;

    public Artcategory() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
