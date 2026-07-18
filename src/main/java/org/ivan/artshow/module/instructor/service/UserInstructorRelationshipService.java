package org.ivan.artshow.module.instructor.service;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.instructor.pojo.Instructor;
import org.ivan.artshow.module.instructor.pojo.UserInstructorRelationship;
import org.ivan.artshow.module.instructor.repository.InstructorRepository;
import org.ivan.artshow.module.instructor.repository.UserInstructorRelationshipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserInstructorRelationshipService - 用户讲师关系服务实现类
 *
 * <p>实现用户与讲师关系的业务逻辑。</p>
 *
 * <p>核心功能：</p>
 * <ul>
 *   <li>在课程报名时自动建立用户与讲师的关系</li>
 *   <li>防止重复创建关系</li>
 *   <li>提供关系查询功能</li>
 *   <li>统计用户的讲师数量和讲师的学生数量</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class UserInstructorRelationshipService implements IUserInstructorRelationshipService {

    private static final Logger log = LoggerFactory.getLogger(UserInstructorRelationshipService.class);

    private final UserInstructorRelationshipRepository relationshipRepository;
    private final InstructorRepository instructorRepository;

    public UserInstructorRelationshipService(
            UserInstructorRelationshipRepository relationshipRepository,
            InstructorRepository instructorRepository) {
        this.relationshipRepository = relationshipRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    @Transactional
    public void createRelationship(Long userId, Long instructorId) {
        if (userId == null || instructorId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 检查关系是否已存在
        if (relationshipRepository.existsByUserIdAndInstructorId(userId, instructorId)) {
            log.debug("用户{}与讲师{}的关系已存在，跳过创建", userId, instructorId);
            return;
        }

        // 验证讲师是否存在
        if (!instructorRepository.existsById(instructorId)) {
            throw new BizException(ResultCodes.NOTFOUND, "讲师不存在");
        }

        // 创建关系
        UserInstructorRelationship relationship = new UserInstructorRelationship();
        relationship.setUserId(userId);
        relationship.setInstructorId(instructorId);
        relationship.setEstablishedAt(new Date());

        relationshipRepository.save(relationship);
        log.info("成功建立用户{}与讲师{}的关系", userId, instructorId);
    }

    @Override
    public boolean hasRelationship(Long userId, Long instructorId) {
        if (userId == null || instructorId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return relationshipRepository.existsByUserIdAndInstructorId(userId, instructorId);
    }

    @Override
    public List<Instructor> findInstructorsByUserId(Long userId) {
        if (userId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 获取讲师ID列表
        List<Long> instructorIds = relationshipRepository.findInstructorIdsByUserId(userId);

        // 查询讲师详情
        return instructorRepository.findAllById(instructorIds);
    }

    @Override
    public List<Long> findStudentIdsByInstructorId(Long instructorId) {
        if (instructorId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return relationshipRepository.findUserIdsByInstructorId(instructorId);
    }

    @Override
    public long countInstructorsByUserId(Long userId) {
        if (userId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return relationshipRepository.countByUserId(userId);
    }

    @Override
    public long countStudentsByInstructorId(Long instructorId) {
        if (instructorId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return relationshipRepository.countByInstructorId(instructorId);
    }
}
