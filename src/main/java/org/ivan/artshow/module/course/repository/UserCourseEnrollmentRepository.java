package org.ivan.artshow.module.course.repository;

import org.ivan.artshow.module.course.pojo.UserCourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * UserCourseEnrollmentRepository - 数据访问接口
 *
 * <p>UserCourseEnrollmentRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface UserCourseEnrollmentRepository extends JpaRepository<UserCourseEnrollment, Long> {
    // 核心方法：检查用户有没有买过这门课
    UserCourseEnrollment findByUserIdAndCourseId(Long userId, Long courseId);

    // 查询我的所有课程
    List<UserCourseEnrollment> findAllByUserId(Long userId);
}
