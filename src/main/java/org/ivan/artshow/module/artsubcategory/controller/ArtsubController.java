package org.ivan.artshow.module.artsubcategory.controller;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.artsubcategory.pojo.Artsubcategory;
import org.ivan.artshow.module.artsubcategory.pojo.dto.ArtsubcategoryDTO;
import org.ivan.artshow.module.artsubcategory.service.ArtsubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ArtsubController - 控制器
 *
 * <p>ArtsubController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/artsub")
public class ArtsubController {
    private final ArtsubService artSubService;

    public ArtsubController(ArtsubService artSubService) {
        this.artSubService = artSubService;
    }

    // 添加子分类 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @PostMapping
    public Result<Artsubcategory> addArtSub(@RequestBody ArtsubcategoryDTO artSubCategory) {
        Artsubcategory nartSub = artSubService.addArtSub(artSubCategory);
        return Result.success(nartSub);
    }

    // 删除子分类 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @DeleteMapping
    public Result<Artsubcategory> delArtSub(@RequestParam Long artsubId) {
        artSubService.deleteArtSub(artsubId);
        return Result.success(null);
    }

    // 查询子分类详情 - 公开
    @Public("子分类详情")
    @GetMapping
    public Result<Artsubcategory> queryArtSub(@RequestParam Long id) {
        return Result.success(artSubService.queryArtSub(id));
    }

    // 更新子分类 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @PutMapping
    public Result<Artsubcategory> updateArtSub(@RequestBody ArtsubcategoryDTO artSubCategory) {
        Artsubcategory artSub = artSubService.updateArtSub(artSubCategory);
        return Result.success(artSub);
    }

    // 查询子分类列表 - 公开
    @Public("子分类列表")
    @GetMapping("/list")
    public Result<List<Artsubcategory>> listSubCategories() {
        return Result.success(artSubService.findAllSubCategories());
    }
}
