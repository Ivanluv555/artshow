package org.ivan.artshow.module.instructor.repository;

import org.ivan.artshow.module.instructor.pojo.UserInstructorRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserInstructorRelationshipRepository - 用户讲师关系数据访问接口
 *
 * <p>提供用户与讲师关系的数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Repository
public interface UserInstructorRelationshipRepository extends JpaRepository<UserInstructorRelationship, Long> {

    /**
     * 检查用户与讲师之间是否已存在关系
     *
     * @param userId 用户ID
     * @param instructorId 讲师ID
     * @return true表示关系已存在，false表示不存在
     */
    boolean existsByUserIdAndInstructorId(Long userId, Long instructorId);

    /**
     * 查询用户的所有讲师关系
     *
     * @param userId 用户ID
     * @return 关系列表
     */
    List<UserInstructorRelationship> findByUserId(Long userId);

    /**
     * 查询讲师的所有学生关系
     *
     * @param instructorId 讲师ID
     * @return 关系列表
     */
    List<UserInstructorRelationship> findByInstructorId(Long instructorId);

    /**
     * 统计用户的讲师数量
     *
     * @param userId 用户ID
     * @return 讲师数量
     */
    long countByUserId(Long userId);

    /**
     * 统计讲师的学生数量
     *
     * @param instructorId 讲师ID
     * @return 学生数量
     */
    long countByInstructorId(Long instructorId);

    /**
     * 查询用户的所有讲师ID列表
     *
     * @param userId 用户ID
     * @return 讲师ID列表
     */
    @Query("SELECT r.instructorId FROM UserInstructorRelationship r WHERE r.userId = :userId")
    List<Long> findInstructorIdsByUserId(@Param("userId") Long userId);

    /**
     * 查询讲师的所有学生ID列表
     *
     * @param instructorId 讲师ID
     * @return 学生ID列表
     */
    @Query("SELECT r.userId FROM UserInstructorRelationship r WHERE r.instructorId = :instructorId")
    List<Long> findUserIdsByInstructorId(@Param("instructorId") Long instructorId);
}
