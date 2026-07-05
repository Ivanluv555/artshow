package org.ivan.artshow.module.course.pojo;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Integer enrollmentId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "certificate_awarded")
    private Boolean certificateAwarded;

    @Column(name = "award_date")
    private Date awardDate;

    @Column(name = "enrolled_at")
    private Date enrolledAt;

    public UserCourseEnrollment() {
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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
