package org.ivan.artshow.module.orderitem.controller;
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
    @PostMapping
    public Result<Orderitem> addOrderItem(@RequestBody @Validated OrderitemDTO orderItem) {
        Orderitem norderitem = orderItemService.addOrderItem(orderItem);
        return Result.success(norderitem);
    }

    @DeleteMapping
    public void deleteOrderItem(@RequestParam Integer orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
    }

    @PutMapping
    public Result<Orderitem> updateOrderItem(@RequestBody @Validated OrderitemDTO orderItem) {
        Orderitem norderitem = orderItemService.updateOrderItem(orderItem);
        return Result.success(norderitem);
    }

    @GetMapping
    public Result<Orderitem> queryOrderItem(@RequestParam Integer orderItemId) {
         Orderitem norderitem = orderItemService.queryOrderItem(orderItemId);
        return Result.success(norderitem);
    }
}
