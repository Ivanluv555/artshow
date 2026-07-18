package org.ivan.artshow.module.instructor.service;

import org.ivan.artshow.module.instructor.pojo.Instructor;

import java.util.List;

/**
 * IUserInstructorRelationshipService - 用户讲师关系服务接口
 *
 * <p>定义用户与讲师关系管理的业务方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IUserInstructorRelationshipService {

    /**
     * 建立用户与讲师的关系
     *
     * <p>此方法应该在课程报名时自动调用，不应该暴露给外部API。</p>
     * <p>如果关系已存在，不会重复创建。</p>
     *
     * @param userId 用户ID
     * @param instructorId 讲师ID
     */
    void createRelationship(Long userId, Long instructorId);

    /**
     * 检查用户与讲师之间是否已有关系
     *
     * @param userId 用户ID
     * @param instructorId 讲师ID
     * @return true表示已有关系，false表示无关系
     */
    boolean hasRelationship(Long userId, Long instructorId);

    /**
     * 查询用户的所有讲师
     *
     * @param userId 用户ID
     * @return 讲师列表
     */
    List<Instructor> findInstructorsByUserId(Long userId);

    /**
     * 查询讲师的所有学生ID列表
     *
     * @param instructorId 讲师ID
     * @return 学生ID列表
     */
    List<Long> findStudentIdsByInstructorId(Long instructorId);

    /**
     * 统计用户的讲师数量
     *
     * @param userId 用户ID
     * @return 讲师数量
     */
    long countInstructorsByUserId(Long userId);

    /**
     * 统计讲师的学生数量
     *
     * @param instructorId 讲师ID
     * @return 学生数量
     */
    long countStudentsByInstructorId(Long instructorId);
}
