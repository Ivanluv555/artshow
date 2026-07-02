package org.ivan.artshow.module.course.pojo.dto;

import lombok.Data;

@Data
/**
 * ChapterCompleteDTO - 数据传输对象
 *
 * <p>ChapterCompleteDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class ChapterCompleteDTO {
    private Integer courseId;  // 前端只知道课程ID
    private Integer chapterId; // 和章节ID
}
