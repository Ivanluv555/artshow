package org.ivan.artshow.module.collection.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "post_collection")
/**
 * Collection - 实体类
 *
 * <p>Collection对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "collection_id")
    private Integer collectionId;
    @Column(name = "post_id")
    @Getter
    @Setter
    private Integer postId;
    @Column(name = "user_id")
    @Getter
    @Setter
    private Integer userId;
    @Column(name = "create_at")
    @Getter
    @Setter
    private Date createAt;

}
