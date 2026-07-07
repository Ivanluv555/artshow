package org.ivan.artshow.module.course.pojo.dto;

/**
 * ChapterCompleteDTO - 数据传输对象
 *
 * <p>ChapterCompleteDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record ChapterCompleteDTO(
        Long courseId,  // 前端只知道课程ID
        Long chapterId  // 和章节ID
) {
    public Long getCourseId() { return courseId; }
    public Long getChapterId() { return chapterId; }
}
