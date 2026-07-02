package org.ivan.artshow.module.course.controller;

import org.ivan.artshow.common.core.result.Result;
import org.ivan.artshow.module.course.pojo.Course;
import org.ivan.artshow.module.course.pojo.dto.CourseDTO;
import org.ivan.artshow.module.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程控制器
 * 提供课程管理的HTTP接口
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    ICourseService courseService;

    /**
     * 添加课程
     * @param course 课程信息DTO
     * @return 新增的课程对象
     */
    @PostMapping
    public Result<Course> addCourse(@RequestBody CourseDTO course){
        Course ncourse = courseService.addCourse(course);
        return Result.success(ncourse);
    }

    /**
     * 删除课程
     * @param courseId 课程ID
     */
    @DeleteMapping
    public void deleteCourse(@RequestParam Integer courseId){
        courseService.deleteCourse(courseId);
    }

    /**
     * 更新课程信息
     * @param course 课程信息DTO
     * @return 更新后的课程对象
     */
    @PutMapping
    public Result<Course> updateCourse(@RequestBody @Validated CourseDTO course){
        Course ncourse = courseService.updateCourse(course);
        return Result.success(ncourse);
    }

    /**
     * 查询单个课程
     * @param courseId 课程ID
     * @return 课程对象
     */
    @GetMapping
    public Result<Course> queryCourse(@RequestParam Integer courseId) {
        Course ncourse = courseService.queryCourse(courseId);
        return Result.success(ncourse);
    }

    /**
     * 批量查询课程
     * @param courseIdList 课程ID列表
     * @return 课程列表
     */
    @PostMapping("/batch")
    public Result<List<Course>> queryAllCourse(@RequestBody List<Integer> courseIdList){
        List<Course> list = courseService.queryAllCourses(courseIdList);
        return Result.success(list);
    }

    /**
     * 查询所有课程
     * @return 课程列表
     */
    @GetMapping("/list")
    public Result<List<Course>> listCourses() {
        return Result.success(courseService.findAllCourses());
    }
}
