package org.ivan.artshow.common.controller;

import org.ivan.artshow.common.auth.Public;
import org.ivan.artshow.common.core.result.Result;
import org.ivan.artshow.common.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计数据控制器
 *
 * <p>提供帖子互动数据查询API</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * 获取帖子的统计数据
     * GET /statistics/post/{postId}
     *
     * @param postId 帖子ID
     * @return 统计数据（点赞数、评论数、浏览数）
     */
    @Public("帖子统计数据")
    @GetMapping("/post/{postId}")
    public Result<Map<String, Long>> getPostStatistics(@PathVariable Long postId) {
        Map<String, Long> stats = new HashMap<>();
        stats.put("likeCount", statisticsService.getPostLikeCount(postId));
        stats.put("commentCount", statisticsService.getPostCommentCount(postId));
        stats.put("viewCount", statisticsService.getPostViewCount(postId));
        return Result.success(stats);
    }

    /**
     * 获取帖子的点赞数
     * GET /statistics/post/{postId}/likes
     *
     * @param postId 帖子ID
     * @return 点赞数
     */
    @Public("帖子点赞数")
    @GetMapping("/post/{postId}/likes")
    public Result<Long> getPostLikeCount(@PathVariable Long postId) {
        return Result.success(statisticsService.getPostLikeCount(postId));
    }

    /**
     * 获取帖子的评论数
     * GET /statistics/post/{postId}/comments
     *
     * @param postId 帖子ID
     * @return 评论数
     */
    @Public("帖子评论数")
    @GetMapping("/post/{postId}/comments")
    public Result<Long> getPostCommentCount(@PathVariable Long postId) {
        return Result.success(statisticsService.getPostCommentCount(postId));
    }

    /**
     * 获取帖子的浏览数
     * GET /statistics/post/{postId}/views
     *
     * @param postId 帖子ID
     * @return 浏览数
     */
    @Public("帖子浏览数")
    @GetMapping("/post/{postId}/views")
    public Result<Long> getPostViewCount(@PathVariable Long postId) {
        return Result.success(statisticsService.getPostViewCount(postId));
    }
}
