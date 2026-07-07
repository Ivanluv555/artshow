package org.ivan.artshow.module.instructor.service;

import org.ivan.artshow.module.instructor.pojo.Instructor;
import org.ivan.artshow.module.instructor.pojo.dto.InstructorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * IInstructorService - 业务服务接口
 *
 * <p>IInstructorService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IInstructorService {
    public Instructor addInstructor(InstructorDTO instructor);
    public Instructor updateInstructor(InstructorDTO instructor);
    public void deleteInstructor(Long UserId);
    public Instructor queryInstructor(Long UserId);
    List<Instructor> findAllInstructors();
}
