package org.ivan.artshow.module.course.repository;

import org.ivan.artshow.module.course.pojo.UserCourseChapterCompleted;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * UserCourseChapterCompletedRepository - 数据访问接口
 *
 * <p>UserCourseChapterCompletedRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface UserCourseChapterCompletedRepository extends JpaRepository<UserCourseChapterCompleted, Integer> {
    // 检查某次报名记录下，某章是否已完成（防止重复打卡）
    boolean existsByEnrollmentIdAndChapterId(Integer enrollmentId, Integer chapterId);

    // 查询某次报名的所有完成记录（用于计算进度）
    List<UserCourseChapterCompleted> findAllByEnrollmentId(Integer enrollmentId);
}
