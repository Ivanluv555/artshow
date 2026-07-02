package org.ivan.artshow.module.orderitem.service;

import org.ivan.artshow.module.orderitem.pojo.Orderitem;
import org.ivan.artshow.module.orderitem.pojo.dto.OrderitemDTO;
import org.ivan.artshow.module.orderitem.repository.OrderitemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * OrderitemService - 业务服务实现类
 *
 * <p>OrderitemService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class OrderitemService implements IOrderitemService {
    private final OrderitemRepository orderItemRepository;

    public OrderitemService(OrderitemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Orderitem addOrderItem(OrderitemDTO orderItem){
        Orderitem norderItem = new Orderitem();
        BeanUtils.copyProperties(orderItem,norderItem);
        return orderItemRepository.save(norderItem);
    }

    @Override
    public void deleteOrderItem(Integer OrderItemId){
        orderItemRepository.deleteById(OrderItemId);
    }

    @Override
    public Orderitem updateOrderItem(OrderitemDTO OrderItem){
        Integer OrderItemId = OrderItem.getOrderItemId();
        Orderitem norderItem = orderItemRepository.findById(OrderItemId).orElseThrow(()-> new RuntimeException("要更新的物品不存在，ID：" + OrderItemId));
        BeanUtils.copyProperties(OrderItem,norderItem);
        return orderItemRepository.save(norderItem);
    }
    @Override
    public Orderitem queryOrderItem(Integer orderItemId) {
        return orderItemRepository.findById(orderItemId).get();
    }

}
