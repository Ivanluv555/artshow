package org.ivan.artshow.module.post.controller;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.post.pojo.Post;
import org.ivan.artshow.module.post.pojo.dto.PostDTO;
import org.ivan.artshow.module.post.service.IPostService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PostController - 控制器
 *
 * <p>PostController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/post")
public class PostController {
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }
    // 发帖 - 需要登录
    @PostMapping
    public Result<Post> addPost(@RequestBody @Validated PostDTO post)
    {
        Post npost = postService.addPost(post);
        return Result.success(npost);
    }

    // 删除帖子 - 需要登录
    @DeleteMapping
    public void deletePost(@RequestParam Long postId){
        postService.deletePost(postId);
    }

    // 更新帖子 - 需要登录
    @PutMapping
    public Result<Post> updatePost(@RequestBody @Validated PostDTO post){
        Post npost = postService.updatePost(post);
        return Result.success(npost);
    }

    // 查询帖子详情 - 公开
    @Public("帖子详情")
    @GetMapping
    public Result<Post> queryPost(@RequestParam Long postId){
        Post npost = postService.queryPost(postId);
        return Result.success(npost);
    }

    // 查询帖子列表 - 公开
    @Public("帖子列表")
    @GetMapping("/list")
    public Result<List<Post>> listPosts() {
        return Result.success(postService.findAllPosts());
    }
}
