package org.ivan.artshow.module.shopcartitem.controller;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.ivan.artshow.module.shopcartitem.pojo.dto.SciDTO;
import org.ivan.artshow.module.shopcartitem.service.ISciService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * SciController - 控制器
 *
 * <p>SciController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sci")
public class SciController {
    private final ISciService sciService;

    public SciController(ISciService sciService) {
        this.sciService = sciService;
    }
    // 添加购物车项 - 需要登录
    @PostMapping
    public Result<Sci> addSci(@RequestBody @Validated SciDTO sci) {
        Sci nsci = sciService.addSci(sci);
        return Result.success(nsci);
    }

    // 删除购物车项 - 需要登录
    @DeleteMapping
    public void deleteSci(@RequestParam Long cartItemId) {
        sciService.deleteSci(cartItemId);
    }

    // 更新购物车项 - 需要登录
    @PutMapping
    public Result<Sci> updateSci(@RequestBody @Validated SciDTO sci) {
        Sci nsci = sciService.updateSci(sci);
        return Result.success(nsci);
    }

    // 查询购物车项 - 需要登录
    @GetMapping
    public Result<Sci> querySci(@RequestParam Long cartItemId) {
        Sci nsci = sciService.querySci(cartItemId);
        return Result.success(nsci);
    }

    // 批量查询购物车 - 需要登录
    @PostMapping("/batch")
    public Result<List<Sci>> queryAllSciBatch(@RequestBody List<Long> userIdList) {
        List<Sci> list = sciService.queryAllSciBatch(userIdList);
        return Result.success(list);
    }

    /**
     * 查询我的购物车 - 需要登录
     * GET /sci/my
     */
    @GetMapping("/my")
    public Result<List<Sci>> getMyCart() {
        List<Sci> myCart = sciService.findMyCart();
        return Result.success(myCart);
    }

    /**
     * 清空我的购物车 - 需要登录
     * DELETE /sci/my
     */
    @DeleteMapping("/my")
    public Result<Void> clearMyCart() {
        sciService.clearMyCart();
        return Result.success(null);
    }

    /**
     * 获取购物车商品种类数 - 需要登录
     * GET /sci/count
     */
    @GetMapping("/count")
    public Result<Integer> getCartItemCount() {
        int count = sciService.getCartItemCount();
        return Result.success(count);
    }
}
