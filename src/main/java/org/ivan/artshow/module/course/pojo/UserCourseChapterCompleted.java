package org.ivan.artshow.module.course.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_course_chapter_completed")
/**
 * UserCourseChapterCompleted - 实体类
 *
 * <p>UserCourseChapterCompleted对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class UserCourseChapterCompleted {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "completion_id") // 修正主键名
    @Getter @Setter
    private Integer completionId;

    @Column(name = "enrollment_id") //
    @Getter @Setter
    private Integer enrollmentId;

    @Column(name = "chapter_id") //
    @Getter @Setter
    private Integer chapterId;
}
