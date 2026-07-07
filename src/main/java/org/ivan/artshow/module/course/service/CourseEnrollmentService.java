package org.ivan.artshow.module.course.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.course.pojo.Course;
import org.ivan.artshow.module.course.pojo.UserCourseChapterCompleted;
import org.ivan.artshow.module.course.pojo.UserCourseEnrollment;
import org.ivan.artshow.module.course.pojo.dto.ChapterCompleteDTO;
import org.ivan.artshow.module.course.pojo.dto.EnrollRequestDTO;
import org.ivan.artshow.module.course.repository.CourseRepository;
import org.ivan.artshow.module.course.repository.UserCourseChapterCompletedRepository;
import org.ivan.artshow.module.course.repository.UserCourseEnrollmentRepository;
import org.ivan.artshow.module.orderitem.repository.OrderitemRepository;
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
    private final CourseRepository courseRepository;
    private final OrderitemRepository orderitemRepository;

    public CourseEnrollmentService(UserCourseEnrollmentRepository enrollmentRepository,
                                   UserCourseChapterCompletedRepository completedRepository,
                                   CourseRepository courseRepository,
                                   OrderitemRepository orderitemRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.completedRepository = completedRepository;
        this.courseRepository = courseRepository;
        this.orderitemRepository = orderitemRepository;
    }

    @Override
    @Transactional
    public UserCourseEnrollment enrollCourse(EnrollRequestDTO enrollDTO) {
        if (enrollDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer userId = UserContext.getUserId();
        Integer courseId = enrollDTO.getCourseId();
        if (courseId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 1. 检查课程是否存在
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "课程不存在"));

        // 2. 检查是否重复报名
        UserCourseEnrollment existing = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if (existing != null) {
            throw new BizException(ResultCodes.INVALID_PARAM, "您已经报名过该课程");
        }

        // 3. 验证课程类型不为空
        if (course.getType() == null) {
            throw new BizException(ResultCodes.INVALID_PARAM, "课程类型未设置");
        }

        // 4. 检查课程类型（核心逻辑）
        if ("paid".equalsIgnoreCase(course.getType())) {
            // 付费课程：检查用户是否已购买
            boolean hasPurchased = orderitemRepository.existsPaidCourseByUserIdAndCourseId(userId, courseId);
            if (!hasPurchased) {
                throw new BizException(ResultCodes.INVALID_PARAM,
                    "该课程为付费课程，请先购买后再报名");
            }
        } else if (!"free".equalsIgnoreCase(course.getType())) {
            // 既不是paid也不是free，数据异常
            throw new BizException(ResultCodes.INVALID_PARAM, "课程类型无效");
        }
        // 免费课程：直接允许报名

        // 5. 创建报名记录
        UserCourseEnrollment enrollment = new UserCourseEnrollment();
        enrollment.setUserId(userId);
        enrollment.setCourseId(courseId);
        enrollment.setCertificateAwarded(false); // 默认未获得证书
        enrollment.setEnrolledAt(new Date());

        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public UserCourseChapterCompleted completeChapter(ChapterCompleteDTO completeDTO) {
        if (completeDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer userId = UserContext.getUserId();
        Integer courseId = completeDTO.getCourseId();
        Integer chapterId = completeDTO.getChapterId();
        if (courseId == null || chapterId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 1. 关键逻辑：必须先查 Enrollment 表，获取 enrollmentId
        UserCourseEnrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if (enrollment == null) {
            throw new BizException(ResultCodes.NOTFOUND, "您还未报名该课程");
        }

        // 2. 检查该章节是否已经打过卡
        boolean isCompleted = completedRepository.existsByEnrollmentIdAndChapterId(enrollment.getEnrollmentId(), chapterId);
        if (isCompleted) {
            throw new BizException(ResultCodes.INVALID_PARAM, "该章节已完成");
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

    @Override
    public boolean hasPurchasedCourse(Integer courseId) {
        if (courseId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer userId = UserContext.getUserId();
        return orderitemRepository.existsPaidCourseByUserIdAndCourseId(userId, courseId);
    }
}
