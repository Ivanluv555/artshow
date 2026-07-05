package org.ivan.artshow.module.course.pojo.dto;

/**
 * EnrollRequestDTO - 数据传输对象
 *
 * <p>EnrollRequestDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record EnrollRequestDTO(
        Integer courseId
) {
    public Integer getCourseId() { return courseId; }
}
