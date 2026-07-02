package org.ivan.artshow.module.badge.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.badge.pojo.Badge;
import org.ivan.artshow.module.badge.pojo.dto.BadgeDTO;
import org.ivan.artshow.module.badge.service.IBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/badge")
/**
 * BadgeController - 控制器
 *
 * <p>BadgeController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class BadgeController {
    @Autowired
    IBadgeService badgeService;
    @PostMapping
    public Result<Badge> addBadge(@RequestBody @Validated BadgeDTO badge) {
        Badge nbadge = badgeService.addBadge(badge);
        return Result.success(nbadge);
    }

    @DeleteMapping
    public void deleteBadge(@RequestParam Integer badgeId) { badgeService.deleteBadge(badgeId);}

    @PutMapping
    public Result<Badge> updateBadge(@RequestBody @Validated BadgeDTO badge) {
        Badge nbadge = badgeService.updateBadge(badge);
        return Result.success(nbadge);
    }

    @GetMapping
    public Result<Badge> queryBadge(@RequestParam Integer badgeId) {
        Badge nbadge = badgeService.queryBadge(badgeId);
        return Result.success(nbadge);}
}
