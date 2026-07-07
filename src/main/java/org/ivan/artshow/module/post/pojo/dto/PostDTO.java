package org.ivan.artshow.module.post.pojo.dto;

import java.util.Date;

/**
 * PostDTO - 数据传输对象
 *
 * <p>PostDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record PostDTO(
        Long postId,
        Integer userId,
        Integer subcategoryId,
        String title,
        String description,
        String imageUrl,
        Date createdAt
) {
    public Long getPostId() { return postId; }
    public Integer getUserId() { return userId; }
    public Integer getSubcategoryId() { return subcategoryId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public Date getCreatedAt() { return createdAt; }
}
