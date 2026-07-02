package org.ivan.artshow.module.course.service;

import org.ivan.artshow.module.course.pojo.Course;
import org.ivan.artshow.module.course.pojo.dto.CourseDTO;
import org.ivan.artshow.module.course.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程服务实现类
 * 实现课程管理的业务逻辑
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class CourseService implements ICourseService{
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course addCourse(CourseDTO course) {
        Course nCourse = new Course();
        BeanUtils.copyProperties(course,nCourse);
        return courseRepository.save(nCourse);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course updateCourse(CourseDTO Course) {
        Integer courseId = Course.getCourseId();
        Course nCourse = courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("没有结果"+courseId));
        BeanUtils.copyProperties(Course,nCourse);
        return courseRepository.save(nCourse);
    }

    @Override
    public Course queryCourse(Integer courseId) {
        return null;
    }

    @Override
    public List<Course> queryAllCourses(List<Integer> courseIdList) {
        return  courseRepository.findAllById(courseIdList);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
}
