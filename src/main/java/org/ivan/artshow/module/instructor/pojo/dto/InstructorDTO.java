package org.ivan.artshow.module.instructor.pojo.dto;

/**
 * InstructorDTO - 数据传输对象
 *
 * <p>InstructorDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record InstructorDTO(
        Long id,
        String name,
        String title,
        String avatarUrl,
        String bio
) {
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getAvatarUrl() { return avatarUrl; }
    public String getBio() { return bio; }
}
