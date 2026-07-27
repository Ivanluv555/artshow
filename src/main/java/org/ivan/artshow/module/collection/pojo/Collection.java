package org.ivan.artshow.module.collection.pojo;

import jakarta.persistence.*;
import org.ivan.artshow.common.config.SnowflakeId;

import java.util.Date;

/**
 * Collection - 实体类
 *
 * <p>Collection对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "post_collection")
public class Collection {
    @Id
    @SnowflakeId
    @Column(name = "collection_id")
    private Long collectionId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_at")
    private Date createAt;

    public Collection() {
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
