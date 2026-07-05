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
        Integer commentId,
        Integer postId,
        Integer userId,
        String content,
        Date createdAt
) {
    public Integer getCommentId() { return commentId; }
    public Integer getPostId() { return postId; }
    public Integer getUserId() { return userId; }
    public String getContent() { return content; }
    public Date getCreatedAt() { return createdAt; }
}
