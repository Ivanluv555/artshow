package org.ivan.artshow.module.like.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.like.pojo.Like;
import org.ivan.artshow.module.like.pojo.dto.LikeDTO;
import org.ivan.artshow.module.like.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
/**
 * LikeController - 控制器
 *
 * <p>LikeController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class LikeController {
    private final LikeService likeservice;

    LikeController(LikeService likeservice) {
        this.likeservice = likeservice;
    }

    @PostMapping
    public Result<Like> addLike(@RequestBody LikeDTO like) {
        Like nLike = likeservice.addLike(like);
        return Result.success(nLike);
    }

    @DeleteMapping
    public Result<Like> deleteLike(@RequestParam Integer likeId) {
        likeservice.deleteLike(likeId);
        return Result.success(null);
    }

    @GetMapping
    public Result<Like> getLike(@RequestParam Integer likeId) {
        Like nlike = likeservice.queryLike(likeId);
        return Result.success(nlike);
    }

    @PutMapping
    public Result<Like> updateLike(@RequestBody LikeDTO like) {
        Like nLike = likeservice.updateLike(like);
        return Result.success(nLike);
    }
}
