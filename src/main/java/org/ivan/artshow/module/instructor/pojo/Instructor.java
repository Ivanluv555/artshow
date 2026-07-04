package org.ivan.artshow.module.instructor.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "course_instructor")
/**
 * Instructor - 实体类
 *
 * <p>Instructor对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Instructor {
    @Id
    @Column(name = "instructor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "title")
    @Getter
    @Setter
    private String title;

    @Column(name = "avatar_url")
    @Getter
    @Setter
    private String avatarUrl;

    @Column(name = "bio")
    @Getter
    @Setter
    private String bio;

    @Column(name = "created_at")
    @Getter
    @Setter
    private Date createdAt;
}
