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
 * InstructorService: 业务服务接口 定义业务方法规范
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
        if (instructor == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
         Instructor ninstructor = new Instructor();
         BeanUtils.copyProperties(instructor,ninstructor);
         return instructorRepository.save(ninstructor);
    }

    @Override
    public Instructor updateInstructor(InstructorDTO instructor) {
        if (instructor == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long instructorId = instructor.getId();
        if (instructorId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Instructor ninstructor = instructorRepository.findById(instructorId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(instructor,ninstructor);
        return instructorRepository.save(ninstructor);
    }

    @Override
    public void deleteInstructor(Long instructorId) {
        if (instructorId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        instructorRepository.deleteById(instructorId);
    }

    @Override
    public Instructor queryInstructor(Long instructorId) {
        if (instructorId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return instructorRepository.findById(instructorId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }
}
