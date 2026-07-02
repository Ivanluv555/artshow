package org.ivan.artshow.module.order.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.order.pojo.Order;
import org.ivan.artshow.module.order.pojo.dto.OrderDTO;
import org.ivan.artshow.module.order.service.IOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderController - 控制器
 *
 * <p>OrderController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    public Result<Order> addOrder(@RequestBody @Validated OrderDTO order) {
        Order norder= orderService.addOrder(order);
        return Result.success(norder);
    }

    @DeleteMapping
    public void deleteOrder(@RequestParam Integer order_id) {
        orderService.deleteOrder(order_id);
    }

    @PutMapping
    public Result<Order> updateOrder(@RequestBody @Validated OrderDTO order) {
        Order norder = orderService.updateOrder(order);
        return Result.success(norder);
    }

    @GetMapping
    public Result<Order> queryOrder(@RequestParam Integer orderId) {
        Order norder = orderService.queryOrder(orderId);
        return Result.success(norder);
    }

    @PostMapping("/batch")
    public Result<List<Order>> queryAllOrderBatch(@RequestBody List<Integer> userIdlist) {
        List<Order> list = orderService.queryAllOrderBatch(userIdlist);
        return Result.success(list);
    }

    @GetMapping("/list") // GET /order/list
    public Result<List<Order>> listOrders() {
        return Result.success(orderService.findAllOrders());
    }

    @GetMapping("/my")
    public Result<List<Order>> queryMyOrders() {
        return Result.success(orderService.findMyOrders());
    }
}
