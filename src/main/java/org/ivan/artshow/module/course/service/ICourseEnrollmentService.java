package org.ivan.artshow.module.course.service;

import org.ivan.artshow.module.course.pojo.UserCourseChapterCompleted;
import org.ivan.artshow.module.course.pojo.UserCourseEnrollment;
import org.ivan.artshow.module.course.pojo.dto.ChapterCompleteDTO;
import org.ivan.artshow.module.course.pojo.dto.EnrollRequestDTO;

import java.util.List;

/**
 * ICourseEnrollmentService - 业务服务接口
 *
 * <p>ICourseEnrollmentService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ICourseEnrollmentService {
    // 报名课程
    UserCourseEnrollment enrollCourse(EnrollRequestDTO enrollDTO);

    // 完成章节打卡
    UserCourseChapterCompleted completeChapter(ChapterCompleteDTO completeDTO);

    // 查询我的课程列表
    List<UserCourseEnrollment> queryMyCourses();
}
