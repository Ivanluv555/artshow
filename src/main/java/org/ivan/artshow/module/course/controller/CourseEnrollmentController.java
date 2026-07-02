package org.ivan.artshow.module.course.controller;

import org.ivan.artshow.common.core.result.Result;
import org.ivan.artshow.module.course.pojo.UserCourseChapterCompleted;
import org.ivan.artshow.module.course.pojo.UserCourseEnrollment;
import org.ivan.artshow.module.course.pojo.dto.ChapterCompleteDTO;
import org.ivan.artshow.module.course.pojo.dto.EnrollRequestDTO;
import org.ivan.artshow.module.course.service.ICourseEnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CourseEnrollmentController - 控制器
 *
 * <p>CourseEnrollmentController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/course/enroll")
public class CourseEnrollmentController {

    private final ICourseEnrollmentService enrollmentService;

    public CourseEnrollmentController(ICourseEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // 1. 报名课程
    // POST /course/enroll
    @PostMapping
    public Result<UserCourseEnrollment> enroll(@RequestBody EnrollRequestDTO enrollDTO) {
        return Result.success(enrollmentService.enrollCourse(enrollDTO));
    }

    // 2. 章节打卡 (完成章节)
    // POST /course/enroll/complete
    @PostMapping("/complete")
    public Result<UserCourseChapterCompleted> completeChapter(@RequestBody ChapterCompleteDTO completeDTO) {
        return Result.success(enrollmentService.completeChapter(completeDTO));
    }

    // 3. 查询我的课程
    // GET /course/enroll/my
    @GetMapping("/my")
    public Result<List<UserCourseEnrollment>> getMyCourses() {
        return Result.success(enrollmentService.queryMyCourses());
    }
}
