package org.ivan.artshow.module.comment.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.comment.pojo.Comment;
import org.ivan.artshow.module.comment.pojo.dto.CommentDTO;
import org.ivan.artshow.module.comment.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * CommentService - 业务服务实现类
 *
 * <p>CommentService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class CommentService implements ICommentService
{
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(CommentDTO comment){
        if (comment == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Comment nComment = new Comment();
        BeanUtils.copyProperties(comment,nComment);
        return commentRepository.save(nComment);
    }

    @Override
    public void deleteComment(Long comment_id){
        if (comment_id == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 查询评论
        Comment comment = commentRepository.findById(comment_id)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "评论不存在"));

        // 权限检查：只有评论作者或管理员可以删除
        Long currentUserId = UserContext.getUserId();
        String currentUserRole = UserContext.getRole();

        if (!comment.getUserId().equals(currentUserId) && !"ADMIN".equals(currentUserRole)) {
            throw new BizException(ResultCodes.FORBIDDEN, "无权删除此评论");
        }

        commentRepository.deleteById(comment_id);
    }

    @Override
    public Comment queryComment(Long comment_id){
        if (comment_id == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return commentRepository.findById(comment_id).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public Comment updateComment(CommentDTO Comment){
        if (Comment == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long commentId = Comment.getCommentId();
        if (commentId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 查询评论
        Comment nComent = commentRepository.findById(commentId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "评论不存在"));

        // 权限检查：只有评论作者或管理员可以修改
        Long currentUserId = UserContext.getUserId();
        String currentUserRole = UserContext.getRole();

        if (!nComent.getUserId().equals(currentUserId) && !"ADMIN".equals(currentUserRole)) {
            throw new BizException(ResultCodes.FORBIDDEN, "无权修改此评论");
        }

        // 更新评论内容（不允许修改userId和postId）
        if (Comment.getContent() != null) {
            nComent.setContent(Comment.getContent());
        }

        return commentRepository.save(nComent);
    }
}
