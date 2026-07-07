package org.ivan.artshow.module.collection.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name = "collection_id")
    private Long collectionId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Integer userId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
