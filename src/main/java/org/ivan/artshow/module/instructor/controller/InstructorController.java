package org.ivan.artshow.module.instructor.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.instructor.pojo.Instructor;
import org.ivan.artshow.module.instructor.pojo.dto.InstructorDTO;
import org.ivan.artshow.module.instructor.service.InstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * InstructorController - 控制器
 *
 * <p>InstructorController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public Result<Instructor> addInstructor(@RequestBody InstructorDTO instructor) {
        Instructor ninstructor = instructorService.addInstructor(instructor);
        return Result.success(ninstructor);
    }

    @DeleteMapping
    public Result<Instructor> deleteInstructor(@RequestParam Integer instructorId) {
        instructorService.deleteInstructor(instructorId);
        return Result.success(null);
    }

    @PutMapping
    public Result<Instructor> updateInstructor(@RequestBody InstructorDTO instructor) {
        Instructor ninstrutor = instructorService.updateInstructor(instructor);
        return Result.success(ninstrutor);
    }

    @GetMapping
    public Result<Instructor> queryInstructor(@RequestParam Integer instructorId) {
        return Result.success(instructorService.queryInstructor(instructorId));
    }

    @GetMapping("/list")
    public Result<List<Instructor>> listInstructors() {
        return Result.success(instructorService.findAllInstructors());
    }
}
