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
        Integer courseId,  // 前端只知道课程ID
        Integer chapterId  // 和章节ID
) {
    public Integer getCourseId() { return courseId; }
    public Integer getChapterId() { return chapterId; }
}
