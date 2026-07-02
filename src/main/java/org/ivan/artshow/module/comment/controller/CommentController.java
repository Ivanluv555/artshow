package org.ivan.artshow.module.comment.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.comment.pojo.Comment;
import org.ivan.artshow.module.comment.pojo.dto.CommentDTO;
import org.ivan.artshow.module.comment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
/**
 * CommentController - 控制器
 *
 * <p>CommentController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class CommentController {
    @Autowired
    ICommentService commentService;
    @PostMapping
    public Result<Comment> addComment(@RequestBody @Validated CommentDTO comment) {
        Comment ncomment= commentService.addComment(comment);
        return Result.success(ncomment);
    }

    @DeleteMapping
    public void deleteComment(@RequestParam Integer commentId) {
        commentService.deleteComment(commentId);
    }

    @PutMapping
    public Result<Comment> updateComment(@RequestBody @Validated CommentDTO comment) {
        Comment ncomment= commentService.updateComment(comment);
        return Result.success(ncomment);
    }

    @GetMapping
    public Result<Comment>  queryComment(@RequestParam  Integer commentId) {
        Comment ncomment= commentService.queryComment(commentId);
        return Result.success(ncomment);
    }
}
