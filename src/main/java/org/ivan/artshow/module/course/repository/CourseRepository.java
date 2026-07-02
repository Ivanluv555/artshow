package org.ivan.artshow.module.course.repository;
import org.ivan.artshow.common.core.result.Result;


import org.ivan.artshow.module.course.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * CourseRepository - 数据访问接口
 *
 * <p>CourseRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
