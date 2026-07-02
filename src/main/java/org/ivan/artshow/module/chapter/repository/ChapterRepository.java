package org.ivan.artshow.module.chapter.repository;

import org.ivan.artshow.module.chapter.pojo.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ChapterRepository - 数据访问接口
 *
 * <p>ChapterRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
}
