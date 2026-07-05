package org.ivan.artshow.module.chapter.pojo.dto;

/**
 * ChapterDTO - 数据传输对象
 *
 * <p>ChapterDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record ChapterDTO(
        Integer chapterId,
        Integer courseId,
        Integer chapterStandId,
        String title,
        String content
) {
    public Integer getChapterId() { return chapterId; }
    public Integer getCourseId() { return courseId; }
    public Integer getChapterStandId() { return chapterStandId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
}
