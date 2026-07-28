package org.ivan.artshow.module.instructor.pojo;

import jakarta.persistence.*;
import org.ivan.artshow.common.config.SnowflakeId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Instructor - 讲师实体类
 *
 * <p>讲师身份信息，关联到user表，一个用户只能有一个讲师身份。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @Column(name = "instructor_id")
    @SnowflakeId
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "bio")
    private String bio;

    @Column(name = "total_students")
    private Integer totalStudents;

    @Column(name = "total_courses")
    private Integer totalCourses;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "status")
    private String status;

    @Column(name = "approved_at")
    private Date approvedAt;

    @Column(name = "created_at")
    private Date createdAt;

    public Instructor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Integer totalCourses) {
        this.totalCourses = totalCourses;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
