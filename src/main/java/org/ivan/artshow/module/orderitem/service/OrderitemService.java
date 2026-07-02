package org.ivan.artshow.module.orderitem.service;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
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
        if (orderItem == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Orderitem norderItem = new Orderitem();
        BeanUtils.copyProperties(orderItem,norderItem);
        return orderItemRepository.save(norderItem);
    }

    @Override
    public void deleteOrderItem(Integer OrderItemId){
        if (OrderItemId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        orderItemRepository.deleteById(OrderItemId);
    }

    @Override
    public Orderitem updateOrderItem(OrderitemDTO OrderItem){
        if (OrderItem == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer OrderItemId = OrderItem.getOrderItemId();
        if (OrderItemId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Orderitem norderItem = orderItemRepository.findById(OrderItemId).orElseThrow(()-> new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(OrderItem,norderItem);
        return orderItemRepository.save(norderItem);
    }
    @Override
    public Orderitem queryOrderItem(Integer orderItemId) {
        if (orderItemId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return orderItemRepository.findById(orderItemId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

}
