package org.ivan.artshow.module.post.controller;
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
    @PostMapping
    public Result<Post> addPost(@RequestBody @Validated PostDTO post)
    {
        Post npost = postService.addPost(post);
        return Result.success(npost);
    }

    @DeleteMapping
    public void deletePost(@RequestParam Integer postId){
        postService.deletePost(postId);
    }

    @PutMapping
    public Result<Post> updatePost(@RequestBody @Validated PostDTO post){
        Post npost = postService.updatePost(post);
        return Result.success(npost);
    }

    @GetMapping
    public Result<Post> queryPost(@RequestParam Integer postId){
        Post npost = postService.queryPost(postId);
        return Result.success(npost);
    }

    @GetMapping("/list")
    public Result<List<Post>> listPosts() {
        return Result.success(postService.findAllPosts());
    }
}
