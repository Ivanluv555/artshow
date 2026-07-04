package org.ivan.artshow.module.course.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "user_course_enrollment")
/**
 * UserCourseEnrollment - 实体类
 *
 * <p>UserCourseEnrollment对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class UserCourseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id") //
    @Getter @Setter
    private Integer enrollmentId;

    @Column(name = "user_id") //
    @Getter @Setter
    private Integer userId;

    @Column(name = "course_id") //
    @Getter @Setter
    private Integer courseId;

    @Column(name = "certificate_awarded")
    @Getter @Setter
    private Boolean certificateAwarded;

    @Column(name = "award_date") //
    @Getter @Setter
    private Date awardDate;

    @Column(name = "enrolled_at")
    @Getter @Setter
    private Date enrolledAt;
}
