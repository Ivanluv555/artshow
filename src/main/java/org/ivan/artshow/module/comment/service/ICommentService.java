package org.ivan.artshow.module.comment.service;

import org.ivan.artshow.module.comment.pojo.Comment;
import org.ivan.artshow.module.comment.pojo.dto.CommentDTO;

/**
 * ICommentService - 业务服务接口
 *
 * <p>ICommentService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ICommentService {
    public Comment addComment(CommentDTO comment);
    public Comment updateComment(CommentDTO Comment);
    public void deleteComment(Long commentId);
    public Comment queryComment(Long commentId);
}
