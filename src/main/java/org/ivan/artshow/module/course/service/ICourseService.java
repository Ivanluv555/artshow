package org.ivan.artshow.module.course.service;

import org.ivan.artshow.module.course.pojo.Course;
import org.ivan.artshow.module.course.pojo.dto.CourseDTO;

import java.util.List;

/**
 * 课程服务接口
 * 定义课程管理的业务方法
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ICourseService {
    /**
     * 添加课程
     * @param course 课程DTO对象
     * @return 新增的课程对象
     */
    Course addCourse(CourseDTO course);

    /**
     * 删除课程
     * @param courseId 课程ID
     */
    void deleteCourse(Integer courseId);

    /**
     * 更新课程信息
     * @param Course 课程DTO对象
     * @return 更新后的课程对象
     */
    Course updateCourse(CourseDTO Course);

    /**
     * 查询单个课程
     * @param courseId 课程ID
     * @return 课程对象
     */
    Course queryCourse(Integer courseId);

    /**
     * 批量查询课程
     * @param courseIdList 课程ID列表
     * @return 课程列表
     */
    List<Course> queryAllCourses(List<Integer> courseIdList);

    /**
     * 查询所有课程
     * @return 课程列表
     */
    List<Course> findAllCourses();
}
