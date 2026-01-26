package org.hyjava.hyall.module.like.pojo;

import org.hyjava.hyall.common.core.result.Result;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "post_like")
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
