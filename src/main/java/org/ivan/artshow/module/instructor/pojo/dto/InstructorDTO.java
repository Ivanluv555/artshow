package org.ivan.artshow.module.instructor.pojo.dto;
import org.ivan.artshow.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

/**
 * InstructorDTO - 数据传输对象
 *
 * <p>InstructorDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
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
