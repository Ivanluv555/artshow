package org.ivan.artshow.common.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 统计服务
 *
 * <p>使用Redis缓存统计数据，包括帖子互动数据（点赞数、评论数等）</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class StatisticsService {

    private final RedisTemplate<String, Object> redisTemplate;

    // Redis Key前缀
    private static final String POST_LIKE_COUNT_PREFIX = "post:like:count:";
    private static final String POST_COMMENT_COUNT_PREFIX = "post:comment:count:";
    private static final String POST_VIEW_COUNT_PREFIX = "post:view:count:";

    public StatisticsService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // ==================== 帖子点赞数 ====================

    /**
     * 增加帖子点赞数
     * @param postId 帖子ID
     * @return 增加后的点赞数
     */
    public Long incrementPostLikeCount(Long postId) {
        String key = POST_LIKE_COUNT_PREFIX + postId;
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 减少帖子点赞数（取消点赞）
     * @param postId 帖子ID
     * @return 减少后的点赞数
     */
    public Long decrementPostLikeCount(Long postId) {
        String key = POST_LIKE_COUNT_PREFIX + postId;
        Long count = redisTemplate.opsForValue().decrement(key, 1);
        // 确保不会出现负数
        if (count != null && count < 0) {
            redisTemplate.opsForValue().set(key, 0);
            return 0L;
        }
        return count;
    }

    /**
     * 获取帖子点赞数
     * @param postId 帖子ID
     * @return 点赞数
     */
    public Long getPostLikeCount(Long postId) {
        String key = POST_LIKE_COUNT_PREFIX + postId;
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? Long.valueOf(value.toString()) : 0L;
    }

    /**
     * 初始化帖子点赞数（从数据库加载）
     * @param postId 帖子ID
     * @param count 初始点赞数
     */
    public void initPostLikeCount(Long postId, Long count) {
        String key = POST_LIKE_COUNT_PREFIX + postId;
        redisTemplate.opsForValue().set(key, count);
    }

    // ==================== 帖子评论数 ====================

    /**
     * 增加帖子评论数
     * @param postId 帖子ID
     * @return 增加后的评论数
     */
    public Long incrementPostCommentCount(Long postId) {
        String key = POST_COMMENT_COUNT_PREFIX + postId;
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 减少帖子评论数（删除评论）
     * @param postId 帖子ID
     * @return 减少后的评论数
     */
    public Long decrementPostCommentCount(Long postId) {
        String key = POST_COMMENT_COUNT_PREFIX + postId;
        Long count = redisTemplate.opsForValue().decrement(key, 1);
        if (count != null && count < 0) {
            redisTemplate.opsForValue().set(key, 0);
            return 0L;
        }
        return count;
    }

    /**
     * 获取帖子评论数
     * @param postId 帖子ID
     * @return 评论数
     */
    public Long getPostCommentCount(Long postId) {
        String key = POST_COMMENT_COUNT_PREFIX + postId;
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? Long.valueOf(value.toString()) : 0L;
    }

    /**
     * 初始化帖子评论数（从数据库加载）
     * @param postId 帖子ID
     * @param count 初始评论数
     */
    public void initPostCommentCount(Long postId, Long count) {
        String key = POST_COMMENT_COUNT_PREFIX + postId;
        redisTemplate.opsForValue().set(key, count);
    }

    // ==================== 帖子浏览数 ====================

    /**
     * 增加帖子浏览数
     * @param postId 帖子ID
     * @return 增加后的浏览数
     */
    public Long incrementPostViewCount(Long postId) {
        String key = POST_VIEW_COUNT_PREFIX + postId;
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 获取帖子浏览数
     * @param postId 帖子ID
     * @return 浏览数
     */
    public Long getPostViewCount(Long postId) {
        String key = POST_VIEW_COUNT_PREFIX + postId;
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? Long.valueOf(value.toString()) : 0L;
    }

    /**
     * 初始化帖子浏览数（从数据库加载）
     * @param postId 帖子ID
     * @param count 初始浏览数
     */
    public void initPostViewCount(Long postId, Long count) {
        String key = POST_VIEW_COUNT_PREFIX + postId;
        redisTemplate.opsForValue().set(key, count);
    }

    // ==================== 批量获取 ====================

    /**
     * 批量初始化帖子统计数据
     * @param postId 帖子ID
     * @param likeCount 点赞数
     * @param commentCount 评论数
     * @param viewCount 浏览数
     */
    public void initPostStats(Long postId, Long likeCount, Long commentCount, Long viewCount) {
        initPostLikeCount(postId, likeCount);
        initPostCommentCount(postId, commentCount);
        initPostViewCount(postId, viewCount);
    }

    /**
     * 删除帖子的所有统计数据
     * @param postId 帖子ID
     */
    public void deletePostStats(Long postId) {
        redisTemplate.delete(POST_LIKE_COUNT_PREFIX + postId);
        redisTemplate.delete(POST_COMMENT_COUNT_PREFIX + postId);
        redisTemplate.delete(POST_VIEW_COUNT_PREFIX + postId);
    }
}
