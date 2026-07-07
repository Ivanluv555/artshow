package org.ivan.artshow.module.orderitem.service;

import org.ivan.artshow.module.orderitem.pojo.Orderitem;
import org.ivan.artshow.module.orderitem.pojo.dto.OrderitemDTO;

/**
 * IOrderitemService - 业务服务接口
 *
 * <p>IOrderitemService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IOrderitemService {
    public Orderitem addOrderItem(OrderitemDTO orderItem);
    public void deleteOrderItem(Long orderItemId);
    public Orderitem updateOrderItem(OrderitemDTO OrderItem);
    public Orderitem queryOrderItem(Long orderItemId);
}
