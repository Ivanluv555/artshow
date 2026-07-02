package org.ivan.artshow.module.course.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "course")
/**
 * Course - 实体类
 *
 * <p>Course对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "course_id")
    private Integer courseId;
    @Getter
    @Setter
    @Column(name = "instructor_id")
    private Integer instructorId;
    @Getter
    @Setter
    @Column(name = "title")
    private String title;
    @Getter
    @Setter
    @Column(name = "cover_image_url")
    private String coverImageUrl;
    @Getter
    @Setter
    @Column(name = "price")
    private Double price;
    @Getter
    @Setter
    @Column(name = "type")
    private String type;
    @Getter
    @Setter
    @Column(name = "student_count")
    private Integer studentCount;
    @Getter
    @Setter
    @Column(name = "description")
    private String description;
}
