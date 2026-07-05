package org.ivan.artshow.module.course.pojo;

import jakarta.persistence.*;
import java.util.Date;

/**
 * UserCourseChapterCompleted - 实体类
 *
 * <p>UserCourseChapterCompleted对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "user_course_chapter_completed")
public class UserCourseChapterCompleted {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "completion_id") // 修正主键名
    private Integer completionId;

    @Column(name = "enrollment_id") //
    private Integer enrollmentId;

    @Column(name = "chapter_id") //
    private Integer chapterId;

    @Column(name = "completed_at")
    private Date completedAt;

    public UserCourseChapterCompleted() {
    }

    public Integer getCompletionId() {
        return completionId;
    }

    public void setCompletionId(Integer completionId) {
        this.completionId = completionId;
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}
