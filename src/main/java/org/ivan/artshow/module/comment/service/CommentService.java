package org.ivan.artshow.module.comment.service;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.comment.pojo.Comment;
import org.ivan.artshow.module.comment.pojo.dto.CommentDTO;
import org.ivan.artshow.module.comment.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * CommentService - 业务服务实现类
 *
 * <p>CommentService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class CommentService implements ICommentService
{
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment addComment(CommentDTO comment){
        Comment nComment = new Comment();
        BeanUtils.copyProperties(comment,nComment);
        return commentRepository.save(nComment);
    }

    @Override
    public void deleteComment(Integer comment_id){commentRepository.deleteById(comment_id);}

    @Override
    public Comment queryComment(Integer comment_id){return commentRepository.findById(comment_id).get();}

    @Override
    public Comment updateComment(CommentDTO Comment){
        Integer commentId = Comment.getCommentId();
        Comment nComent=commentRepository.findById(commentId).orElseThrow(()->new RuntimeException("无结果"+commentId));
        BeanUtils.copyProperties(Comment,nComent);
        return commentRepository.save(nComent);
    }
}
