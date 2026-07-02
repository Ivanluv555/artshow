package org.ivan.artshow.module.post.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * PostDTO - 数据传输对象
 *
 * <p>PostDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class PostDTO {
    @Getter
    @Setter
    private Integer postId;
    @Getter
    @Setter
    private Integer userId;
    @Getter
    @Setter
    private Integer subcategoryId;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String imageUrl;
    @Getter
    @Setter
    private Date createdAt;
}
