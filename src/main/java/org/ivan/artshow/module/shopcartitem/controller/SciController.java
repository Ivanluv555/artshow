package org.ivan.artshow.module.shopcartitem.controller;

import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.ivan.artshow.module.shopcartitem.pojo.dto.CartItemWithProductDTO;
import org.ivan.artshow.module.shopcartitem.pojo.dto.SciDTO;
import org.ivan.artshow.module.shopcartitem.service.ISciService;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SciController - 控制器
 *
 * <p>
 * SciController负责处理HTTP请求，提供RESTful API接口。
 * </p>
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
     *
     * 返回简单的购物车项列表，不包含商品详情，按创建时间倒序
     */
    @GetMapping("/my")
    public Result<List<Sci>> getMyCart() {
        List<Sci> myCart = sciService.findMyCart();
        return Result.success(myCart);
    }

    /**
     * 分页查询我的购物车（包含商品信息）- 需要登录
     * GET /sci/my/page
     *
     * 支持分页，关联查询商品详细信息（半懒加载），按创建时间倒序
     *
     * @param page 页码（从0开始），默认0
     * @param size 每页大小，默认10
     * @return 分页结果，包含购物车项及关联的商品信息
     */
    @GetMapping("/my/page")
    public Result<Page<CartItemWithProductDTO>> getMyCartWithProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CartItemWithProductDTO> cartPage = sciService.findMyCartWithProducts(page, size);
        return Result.success(cartPage);
    }

    /**
     * 批量删除购物车项 - 需要登录
     * DELETE /sci/batch
     *
     * @param cartItemIds 购物车项ID列表
     */
    @DeleteMapping("/batch")
    public Result<Void> batchDeleteCartItems(@RequestBody List<Long> cartItemIds) {
        sciService.batchDeleteCartItems(cartItemIds);
        return Result.success(null);
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
