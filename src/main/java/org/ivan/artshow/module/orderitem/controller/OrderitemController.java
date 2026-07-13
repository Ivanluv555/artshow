package org.ivan.artshow.module.orderitem.controller;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.orderitem.pojo.Orderitem;
import org.ivan.artshow.module.orderitem.pojo.dto.OrderitemDTO;
import org.ivan.artshow.module.orderitem.service.IOrderitemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * OrderitemController - 控制器
 *
 * <p>OrderitemController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/orderitem")
public class OrderitemController {
    private final IOrderitemService orderItemService;

    public OrderitemController(IOrderitemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    // 添加订单项 - 需要登录
    @PostMapping
    public Result<Orderitem> addOrderItem(@RequestBody @Validated OrderitemDTO orderItem) {
        Orderitem norderitem = orderItemService.addOrderItem(orderItem);
        return Result.success(norderitem);
    }

    // 删除订单项 - 需要登录
    @DeleteMapping
    public void deleteOrderItem(@RequestParam Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
    }

    // 更新订单项 - 需要登录
    @PutMapping
    public Result<Orderitem> updateOrderItem(@RequestBody @Validated OrderitemDTO orderItem) {
        Orderitem norderitem = orderItemService.updateOrderItem(orderItem);
        return Result.success(norderitem);
    }

    // 查询订单项 - 需要登录
    @GetMapping
    public Result<Orderitem> queryOrderItem(@RequestParam Long orderItemId) {
         Orderitem norderitem = orderItemService.queryOrderItem(orderItemId);
        return Result.success(norderitem);
    }
}
