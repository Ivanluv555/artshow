package org.hyjava.hyall.module.course.pojo.dto;
import org.hyjava.hyall.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

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
