package org.ivan.artshow.module.instructor.repository;

import org.ivan.artshow.module.instructor.pojo.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * InstructorRepository - 数据访问接口
 *
 * <p>InstructorRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
