package org.ivan.artshow.module.like.pojo.dto;

import java.util.Date;

/**
 * LikeDTO - 数据传输对象
 *
 * <p>LikeDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record LikeDTO(
        Long id,
        int postId,
        int userId,
        Date createdAt
) {
    public Long getId() { return id; }
    public int getPostId() { return postId; }
    public int getUserId() { return userId; }
    public Date getCreatedAt() { return createdAt; }
}
