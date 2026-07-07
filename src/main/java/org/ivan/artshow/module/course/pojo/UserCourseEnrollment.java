package org.ivan.artshow.module.course.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;

/**
 * UserCourseEnrollment - 实体类
 *
 * <p>UserCourseEnrollment对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "user_course_enrollment")
public class UserCourseEnrollment {
    @Id
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "certificate_awarded")
    private Boolean certificateAwarded;

    @Column(name = "award_date")
    private Date awardDate;

    @Column(name = "enrolled_at")
    private Date enrolledAt;

    public UserCourseEnrollment() {
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Boolean getCertificateAwarded() {
        return certificateAwarded;
    }

    public void setCertificateAwarded(Boolean certificateAwarded) {
        this.certificateAwarded = certificateAwarded;
    }

    public Date getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(Date awardDate) {
        this.awardDate = awardDate;
    }

    public Date getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Date enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
