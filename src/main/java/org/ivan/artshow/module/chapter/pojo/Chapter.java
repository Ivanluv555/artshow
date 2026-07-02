package org.ivan.artshow.module.chapter.pojo;
import org.ivan.artshow.common.core.result.Result;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "course_outline")
/**
 * Chapter - 实体类
 *
 * <p>Chapter对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "chapter_id")
    private Integer chapterId;
    @Getter
    @Setter
    @Column(name = "course_id")
    private Integer courseId;
    @Getter
    @Setter
    @Column(name = "chapter_stand_id")
    private Integer chapterStandId;
    @Getter
    @Setter
    @Column(name = "title")
    private String title;
    @Getter
    @Setter
    @Column(name = "content")
    private String content;
}
