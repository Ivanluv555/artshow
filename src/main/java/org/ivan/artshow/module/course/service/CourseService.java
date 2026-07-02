package org.ivan.artshow.module.course.service;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
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
        if (course == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Course nCourse = new Course();
        BeanUtils.copyProperties(course,nCourse);
        return courseRepository.save(nCourse);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        if (courseId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course updateCourse(CourseDTO Course) {
        if (Course == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer courseId = Course.getCourseId();
        if (courseId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Course nCourse = courseRepository.findById(courseId).orElseThrow(()->new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(Course,nCourse);
        return courseRepository.save(nCourse);
    }

    @Override
    public Course queryCourse(Integer courseId) {
        if (courseId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return courseRepository.findById(courseId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Course> queryAllCourses(List<Integer> courseIdList) {
        if (courseIdList == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return  courseRepository.findAllById(courseIdList);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
}
