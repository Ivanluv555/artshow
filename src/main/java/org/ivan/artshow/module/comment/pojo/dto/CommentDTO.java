package org.ivan.artshow.module.comment.pojo.dto;

import java.util.Date;

/**
 * CommentDTO - 数据传输对象
 *
 * <p>CommentDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record CommentDTO(
        Long commentId,
        Long postId,
        Long userId,
        String content,
        Date createdAt
) {
    public Long getCommentId() { return commentId; }
    public Long getPostId() { return postId; }
    public Long getUserId() { return userId; }
    public String getContent() { return content; }
    public Date getCreatedAt() { return createdAt; }
}
