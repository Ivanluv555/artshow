package org.ivan.artshow.module.instructor.service;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.instructor.pojo.Instructor;
import org.ivan.artshow.module.instructor.pojo.dto.InstructorDTO;
import org.ivan.artshow.module.instructor.repository.InstructorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * InstructorService - 业务服务接口
 *
 * <p>InstructorService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class InstructorService implements IInstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor addInstructor(InstructorDTO instructor) {
         Instructor ninstructor = new Instructor();
         BeanUtils.copyProperties(instructor,ninstructor);
         return instructorRepository.save(ninstructor);
    }

    @Override
    public Instructor updateInstructor(InstructorDTO instructor) {
        Integer instructorId = instructor.getId();
        Instructor ninstructor = instructorRepository.findById(instructorId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(instructor,ninstructor);
        return instructorRepository.save(ninstructor);
    }

    @Override
    public void deleteInstructor(Integer instructorId) {
        instructorRepository.deleteById(instructorId);
    }

    @Override
    public Instructor queryInstructor(Integer instructorId) {
        return instructorRepository.findById(instructorId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }
}
