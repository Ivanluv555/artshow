package org.ivan.artshow.module.comment.pojo;
import org.ivan.artshow.common.core.result.Result;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "post_comment")
/**
 * Comment - 实体类
 *
 * <p>Comment对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "comment_id")
    private Integer commentId;
    @Getter
    @Setter
    @Column(name = "post_id")
    private Integer postId;
    @Getter
    @Setter
    @Column(name = "user_id")
    private Integer userId;
    @Getter
    @Setter
    @Column(name = "content")
    private String content;
    @Getter
    @Setter
    @Column(name = "created_at")
    private Date createdAt;
}
