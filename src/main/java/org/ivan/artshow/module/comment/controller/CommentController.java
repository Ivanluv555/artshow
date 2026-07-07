package org.ivan.artshow.module.comment.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.comment.pojo.Comment;
import org.ivan.artshow.module.comment.pojo.dto.CommentDTO;
import org.ivan.artshow.module.comment.service.ICommentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * CommentController - 控制器
 *
 * <p>CommentController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public Result<Comment> addComment(@RequestBody @Validated CommentDTO comment) {
        Comment ncomment= commentService.addComment(comment);
        return Result.success(ncomment);
    }

    @DeleteMapping
    public void deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentId);
    }

    @PutMapping
    public Result<Comment> updateComment(@RequestBody @Validated CommentDTO comment) {
        Comment ncomment= commentService.updateComment(comment);
        return Result.success(ncomment);
    }

    @GetMapping
    public Result<Comment>  queryComment(@RequestParam  Long commentId) {
        Comment ncomment= commentService.queryComment(commentId);
        return Result.success(ncomment);
    }
}
