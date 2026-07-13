package org.ivan.artshow.module.instructor.controller;
import org.ivan.artshow.common.auth.*;
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

    // 添加讲师 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @PostMapping
    public Result<Instructor> addInstructor(@RequestBody InstructorDTO instructor) {
        Instructor ninstructor = instructorService.addInstructor(instructor);
        return Result.success(ninstructor);
    }

    // 删除讲师 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @DeleteMapping
    public Result<Instructor> deleteInstructor(@RequestParam Long instructorId) {
        instructorService.deleteInstructor(instructorId);
        return Result.success(null);
    }

    // 更新讲师 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @PutMapping
    public Result<Instructor> updateInstructor(@RequestBody InstructorDTO instructor) {
        Instructor ninstrutor = instructorService.updateInstructor(instructor);
        return Result.success(ninstrutor);
    }

    // 查询讲师详情 - 公开
    @Public("讲师详情")
    @GetMapping
    public Result<Instructor> queryInstructor(@RequestParam Long instructorId) {
        return Result.success(instructorService.queryInstructor(instructorId));
    }

    // 查询讲师列表 - 公开
    @Public("讲师列表")
    @GetMapping("/list")
    public Result<List<Instructor>> listInstructors() {
        return Result.success(instructorService.findAllInstructors());
    }
}
