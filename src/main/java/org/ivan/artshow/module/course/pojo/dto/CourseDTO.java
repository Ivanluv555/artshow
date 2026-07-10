package org.ivan.artshow.module.course.pojo.dto;

import java.math.BigDecimal;

/**
 * CourseDTO - 数据传输对象
 *
 * <p>CourseDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record CourseDTO(
        Long courseId,
        Integer instructorId,
        String title,
        String coverImageUrl,
        BigDecimal price,
        String type,
        Integer studentCount,
        String description
) {
    public Long getCourseId() { return courseId; }
    public Integer getInstructorId() { return instructorId; }
    public String getTitle() { return title; }
    public String getCoverImageUrl() { return coverImageUrl; }
    public BigDecimal getPrice() { return price; }
    public String getType() { return type; }
    public Integer getStudentCount() { return studentCount; }
    public String getDescription() { return description; }
}
