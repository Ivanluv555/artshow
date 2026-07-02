package org.ivan.artshow.module.course.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.course.pojo.UserCourseChapterCompleted;
import org.ivan.artshow.module.course.pojo.UserCourseEnrollment;
import org.ivan.artshow.module.course.pojo.dto.ChapterCompleteDTO;
import org.ivan.artshow.module.course.pojo.dto.EnrollRequestDTO;
import org.ivan.artshow.module.course.repository.UserCourseChapterCompletedRepository;
import org.ivan.artshow.module.course.repository.UserCourseEnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * CourseEnrollmentService - 业务服务实现类
 *
 * <p>CourseEnrollmentService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class CourseEnrollmentService implements ICourseEnrollmentService {

    private final UserCourseEnrollmentRepository enrollmentRepository;
    private final UserCourseChapterCompletedRepository completedRepository;

    public CourseEnrollmentService(UserCourseEnrollmentRepository enrollmentRepository,
                                   UserCourseChapterCompletedRepository completedRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.completedRepository = completedRepository;
    }

    @Override
    @Transactional
    public UserCourseEnrollment enrollCourse(EnrollRequestDTO enrollDTO) {
        Integer userId = UserContext.getUserId();
        Integer courseId = enrollDTO.getCourseId();

        // 1. 检查是否重复报名
        UserCourseEnrollment existing = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if (existing != null) {
            throw new BizException(ResultCodes.OVERTIME);
        }

        // 2. 创建报名记录
        UserCourseEnrollment enrollment = new UserCourseEnrollment();
        enrollment.setUserId(userId);
        enrollment.setCourseId(courseId);
        enrollment.setCertificateAwarded(false); // 默认未获得证书

        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public UserCourseChapterCompleted completeChapter(ChapterCompleteDTO completeDTO) {
        Integer userId = UserContext.getUserId();
        Integer courseId = completeDTO.getCourseId();
        Integer chapterId = completeDTO.getChapterId();

        // 1. 关键逻辑：必须先查 Enrollment 表，获取 enrollmentId
        UserCourseEnrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if (enrollment == null) {
            throw new BizException(ResultCodes.NOTFOUND);
        }

        // 2. 检查该章节是否已经打过卡
        boolean isCompleted = completedRepository.existsByEnrollmentIdAndChapterId(enrollment.getEnrollmentId(), chapterId);
        if (isCompleted) {
            throw new BizException(ResultCodes.NOTFOUND);
        }

        // 3. 保存打卡记录
        UserCourseChapterCompleted completed = new UserCourseChapterCompleted();
        completed.setEnrollmentId(enrollment.getEnrollmentId()); // 关联到报名记录ID
        completed.setChapterId(chapterId);
        return completedRepository.save(completed);
    }

    @Override
    public List<UserCourseEnrollment> queryMyCourses() {
        Integer userId = UserContext.getUserId();
        return enrollmentRepository.findAllByUserId(userId);
    }
}
