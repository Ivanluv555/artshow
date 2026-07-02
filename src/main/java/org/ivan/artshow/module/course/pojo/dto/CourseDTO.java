package org.ivan.artshow.module.course.pojo.dto;
import org.ivan.artshow.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

/**
 * CourseDTO - 数据传输对象
 *
 * <p>CourseDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class CourseDTO {
    @Getter
    @Setter
    private Integer courseId;
    @Getter
    @Setter
    private Integer instructorId;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String coverImageUrl;
    @Getter
    @Setter
    private Double price;
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private Integer studentCount;
    @Getter
    @Setter
    private String description;
}
