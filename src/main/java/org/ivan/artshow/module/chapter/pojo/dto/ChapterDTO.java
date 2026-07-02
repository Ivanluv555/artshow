package org.ivan.artshow.module.chapter.pojo.dto;
import org.ivan.artshow.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

/**
 * ChapterDTO - 数据传输对象
 *
 * <p>ChapterDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class ChapterDTO {
    @Getter
    @Setter
    private Integer chapterId;
    @Getter
    @Setter
    private Integer courseId;
    @Getter
    @Setter
    private Integer chapterStandId;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String content;
}
