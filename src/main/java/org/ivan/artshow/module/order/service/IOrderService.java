package org.ivan.artshow.module.order.service;
import org.ivan.artshow.common.core.result.Result;


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
    public void deleteOrder(Integer orderId);
    public Order updateOrder(OrderDTO Order);
    public Order queryOrder(Integer orderId);
    public List<Order> queryAllOrderBatch(List<Integer> userIdlist);
    List<Order> findAllOrders();
    List<Order> findMyOrders();
}
