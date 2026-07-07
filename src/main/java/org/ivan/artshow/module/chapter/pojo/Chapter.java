package org.ivan.artshow.module.chapter.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * Chapter - 实体类
 *
 * <p>Chapter对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "course_outline")
public class Chapter {
    @Id
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name = "chapter_id")
    private Long chapterId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "chapter_stand_id")
    private Integer chapterStandId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Date createdAt;

    public Chapter() {
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getChapterStandId() {
        return chapterStandId;
    }

    public void setChapterStandId(Integer chapterStandId) {
        this.chapterStandId = chapterStandId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
