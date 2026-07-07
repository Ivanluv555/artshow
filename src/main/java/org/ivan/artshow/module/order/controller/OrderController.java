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
    public void deleteOrder(@RequestParam Long order_id) {
        orderService.deleteOrder(order_id);
    }

    @PutMapping
    public Result<Order> updateOrder(@RequestBody @Validated OrderDTO order) {
        Order norder = orderService.updateOrder(order);
        return Result.success(norder);
    }

    @GetMapping
    public Result<Order> queryOrder(@RequestParam Long orderId) {
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

    /**
     * 从购物车创建订单
     * POST /order/create-from-cart
     */
    @PostMapping("/create-from-cart")
    public Result<Order> createOrderFromCart(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrderFromCart(request.getCartItemIds(), request.getAddressId());
        return Result.success(order);
    }

    /**
     * 直接购买课程
     * POST /order/purchase-course/{courseId}
     */
    @PostMapping("/purchase-course/{courseId}")
    public Result<Order> purchaseCourse(@PathVariable Integer courseId) {
        Order order = orderService.purchaseCourse(courseId);
        return Result.success(order);
    }

    /**
     * 取消订单
     * PUT /order/{orderId}/cancel
     */
    @PutMapping("/{orderId}/cancel")
    public Result<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return Result.success(null);
    }

    /**
     * 支付订单
     * PUT /order/{orderId}/pay
     */
    @PutMapping("/{orderId}/pay")
    public Result<Void> payOrder(@PathVariable Long orderId) {
        orderService.payOrder(orderId);
        return Result.success(null);
    }

    /**
     * 发货
     * PUT /order/{orderId}/ship
     */
    @PutMapping("/{orderId}/ship")
    public Result<Void> shipOrder(@PathVariable Long orderId) {
        orderService.shipOrder(orderId);
        return Result.success(null);
    }

    /**
     * 完成订单
     * PUT /order/{orderId}/complete
     */
    @PutMapping("/{orderId}/complete")
    public Result<Void> completeOrder(@PathVariable Long orderId) {
        orderService.completeOrder(orderId);
        return Result.success(null);
    }

    /**
     * 创建订单请求对象
     */
    public static class CreateOrderRequest {
        private List<Long> cartItemIds;
        private Long addressId;

        public List<Long> getCartItemIds() {
            return cartItemIds;
        }

        public void setCartItemIds(List<Long> cartItemIds) {
            this.cartItemIds = cartItemIds;
        }

        public Long getAddressId() {
            return addressId;
        }

        public void setAddressId(Long addressId) {
            this.addressId = addressId;
        }
    }
}
