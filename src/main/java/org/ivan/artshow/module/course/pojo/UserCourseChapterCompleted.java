package org.ivan.artshow.module.course.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
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
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name = "completion_id") // 修正主键名
    private Long completionId;

    @Column(name = "enrollment_id") //
    private Long enrollmentId;

    @Column(name = "chapter_id") //
    private Long chapterId;

    @Column(name = "completed_at")
    private Date completedAt;

    public UserCourseChapterCompleted() {
    }

    public Long getCompletionId() {
        return completionId;
    }

    public void setCompletionId(Long completionId) {
        this.completionId = completionId;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}
