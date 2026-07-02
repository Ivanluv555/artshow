package org.ivan.artshow.module.like.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "post_like")
/**
 * Like - 实体类
 *
 * <p>Like对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "post_id")
    @Getter
    @Setter
    private Integer postId;

    @Column(name = "user_id")
    @Getter
    @Setter
    private Integer userId;

    @Column(name = "created_at")
    @Getter
    @Setter
    private Date createdAt;
}
