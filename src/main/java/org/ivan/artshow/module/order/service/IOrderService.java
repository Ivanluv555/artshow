package org.ivan.artshow.module.order.service;

import org.ivan.artshow.module.order.pojo.Order;
import org.ivan.artshow.module.order.pojo.dto.OrderDTO;

import java.util.List;

/**
 * IOrderService - 业务服务接口
 *
 * <p>IOrderService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IOrderService {
    public Order addOrder(OrderDTO order);
    public void deleteOrder(Long orderId);
    public Order updateOrder(OrderDTO Order);
    public Order queryOrder(Long orderId);
    public List<Order> queryAllOrderBatch(List<Integer> userIdlist);
    List<Order> findAllOrders();
    List<Order> findMyOrders();

    /**
     * 从购物车创建订单（完整流程）
     * @param cartItemIds 购物车项ID列表
     * @param addressId 收货地址ID
     * @return 创建的订单
     */
    Order createOrderFromCart(List<Long> cartItemIds, Long addressId);

    /**
     * 直接购买课程（创建课程订单）
     * @param courseId 课程ID
     * @return 创建的订单
     */
    Order purchaseCourse(Integer courseId);

    /**
     * 取消订单（恢复库存）
     * @param orderId 订单ID
     */
    void cancelOrder(Long orderId);

    /**
     * 支付订单
     * @param orderId 订单ID
     */
    void payOrder(Long orderId);

    /**
     * 发货
     * @param orderId 订单ID
     */
    void shipOrder(Long orderId);

    /**
     * 完成订单
     * @param orderId 订单ID
     */
    void completeOrder(Long orderId);
}
