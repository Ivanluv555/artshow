package org.ivan.artshow.module.artcategory.controller;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.artcategory.pojo.Artcategory;
import org.ivan.artshow.module.artcategory.pojo.dto.ArtcategoryDTO;
import org.ivan.artshow.module.artcategory.service.IArtcategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ArtcategoryController - 控制器
 *
 * <p>ArtcategoryController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/artcate")
public class ArtcategoryController {
    private final IArtcategoryService artCateService;

    public ArtcategoryController(IArtcategoryService artCateService) {
        this.artCateService = artCateService;
    }

    // 添加分类 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @PostMapping
    public Result<Artcategory> addCate(@RequestBody @Validated ArtcategoryDTO artcategory) {
        Artcategory nartcategory = artCateService.addCate(artcategory);
        return Result.success(nartcategory);
    }

    // 更新分类 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @PutMapping
    public Result<Artcategory> updateCate(@RequestBody @Validated ArtcategoryDTO artcategory) {
        Artcategory nartcategory = artCateService.updateCate(artcategory);
        return Result.success(nartcategory);
    }

    // 删除分类 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @DeleteMapping
    public Result<Artcategory> deleteCate(@RequestParam Long id) {
        artCateService.deleteCate(id);
        return Result.success(null);
    }

    // 查询分类详情 - 公开
    @Public("分类详情")
    @GetMapping
    public Result<Artcategory> getCate(@RequestParam Long cateId) {
        Artcategory nartCategory = artCateService.queryCate(cateId);
        return Result.success(nartCategory);
    }

    // 查询分类列表 - 公开
    @Public("分类列表")
    @GetMapping("/list")
    public Result<List<Artcategory>> listCategories() {
        return Result.success(artCateService.findAllCategories());
    }
}
