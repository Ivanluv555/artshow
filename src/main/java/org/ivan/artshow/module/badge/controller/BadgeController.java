package org.ivan.artshow.module.badge.controller;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.badge.pojo.Badge;
import org.ivan.artshow.module.badge.pojo.dto.BadgeDTO;
import org.ivan.artshow.module.badge.service.IBadgeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * BadgeController - 控制器
 *
 * <p>BadgeController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/badge")
public class BadgeController {
    private final IBadgeService badgeService;

    public BadgeController(IBadgeService badgeService) {
        this.badgeService = badgeService;
    }
    // 添加徽章 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @PostMapping
    public Result<Badge> addBadge(@RequestBody @Validated BadgeDTO badge) {
        Badge nbadge = badgeService.addBadge(badge);
        return Result.success(nbadge);
    }

    // 删除徽章 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @DeleteMapping
    public void deleteBadge(@RequestParam Long badgeId) { badgeService.deleteBadge(badgeId);}

    // 更新徽章 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @PutMapping
    public Result<Badge> updateBadge(@RequestBody @Validated BadgeDTO badge) {
        Badge nbadge = badgeService.updateBadge(badge);
        return Result.success(nbadge);
    }

    // 查询徽章 - 需要登录
    @GetMapping
    public Result<Badge> queryBadge(@RequestParam Long badgeId) {
        Badge nbadge = badgeService.queryBadge(badgeId);
        return Result.success(nbadge);}
}
