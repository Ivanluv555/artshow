package org.hyjava.hyall.module.instructor.pojo.dto;
import org.hyjava.hyall.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

public class InstructorDTO {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String avatarUrl;

    @Getter
    @Setter
    private String bio;
}
