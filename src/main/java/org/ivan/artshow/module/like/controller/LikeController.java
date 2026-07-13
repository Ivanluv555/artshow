package org.ivan.artshow.module.like.controller;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.like.pojo.Like;
import org.ivan.artshow.module.like.pojo.dto.LikeDTO;
import org.ivan.artshow.module.like.service.LikeService;
import org.springframework.web.bind.annotation.*;

/**
 * LikeController - 控制器
 *
 * <p>LikeController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // 添加点赞 - 需要登录
    @PostMapping
    public Result<Like> addLike(@RequestBody LikeDTO like) {
        Like nLike = likeService.addLike(like);
        return Result.success(nLike);
    }

    // 删除点赞 - 需要登录
    @DeleteMapping
    public Result<Like> deleteLike(@RequestParam Long likeId) {
        likeService.deleteLike(likeId);
        return Result.success(null);
    }

    // 查询点赞 - 需要登录
    @GetMapping
    public Result<Like> getLike(@RequestParam Long likeId) {
        Like nlike = likeService.queryLike(likeId);
        return Result.success(nlike);
    }

    // 更新点赞 - 需要登录
    @PutMapping
    public Result<Like> updateLike(@RequestBody LikeDTO like) {
        Like nLike = likeService.updateLike(like);
        return Result.success(nLike);
    }
}
