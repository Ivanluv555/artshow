package org.ivan.artshow.module.artcategory.controller;
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

    @PostMapping
    public Result<Artcategory> addCate(@RequestBody @Validated ArtcategoryDTO artcategory) {
        Artcategory nartcategory = artCateService.addCate(artcategory);
        return Result.success(nartcategory);
    }

    @PutMapping
    public Result<Artcategory> updateCate(@RequestBody @Validated ArtcategoryDTO artcategory) {
        Artcategory nartcategory = artCateService.updateCate(artcategory);
        return Result.success(nartcategory);
    }

    @DeleteMapping
    public Result<Artcategory> deleteCate(@RequestParam Integer id) {
        artCateService.deleteCate(id);
        return Result.success(null);
    }

    @GetMapping
    public Result<Artcategory> getCate(@RequestParam Integer cateId) {
        Artcategory nartCategory = artCateService.queryCate(cateId);
        return Result.success(nartCategory);
    }

    @GetMapping("/list")
    public Result<List<Artcategory>> listCategories() {
        return Result.success(artCateService.findAllCategories());
    }
}
