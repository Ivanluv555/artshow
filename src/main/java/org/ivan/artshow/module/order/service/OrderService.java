package org.ivan.artshow.module.order.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.address.pojo.Address;
import org.ivan.artshow.module.address.repository.AddRepository;
import org.ivan.artshow.module.order.pojo.Order;
import org.ivan.artshow.module.order.pojo.OrderShippingAddress;
import org.ivan.artshow.module.order.pojo.dto.OrderDTO;
import org.ivan.artshow.module.order.repository.OrderRepository;
import org.ivan.artshow.module.order.repository.OrderShippingAddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
/**
 * OrderService - 业务服务实现类
 *
 * <p>OrderService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AddRepository addRepository;
    @Autowired
    OrderShippingAddressRepository shippingAddressRepository;

    @Override
    @Transactional
    public Order addOrder(OrderDTO orderDTO) {
        Integer currentUserId = UserContext.getUserId();

        Order nOrder = new Order();
        BeanUtils.copyProperties(orderDTO, nOrder);
        nOrder.setUserId(currentUserId); // 🔒 强制绑定当前用户
        nOrder.setCreatedAt(new Date());
        nOrder = orderRepository.save(nOrder);

        // 处理地址快照
        if (orderDTO.getAddressId() != null) {
            Address userAddr = addRepository.findById(orderDTO.getAddressId())
                    .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

            // 🔒 额外检查：下单用的地址必须是自己的
            if (!userAddr.getUserId().equals(currentUserId)) {
                throw new BizException(ResultCodes.UNAUTH);
            }

            OrderShippingAddress snapshot = new OrderShippingAddress();
            snapshot.setOrderId(nOrder.getOrderId());
            snapshot.setRecipientName(userAddr.getRecipientName());
            snapshot.setPhone(userAddr.getPhone());
            snapshot.setRegion(userAddr.getRegion());
            snapshot.setDetail(userAddr.getDetailAddress());
            shippingAddressRepository.save(snapshot);
        }
        return nOrder;
    }

    @Override
    public void deleteOrder(Integer orderId) {
        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查
        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order updateOrder(OrderDTO orderDTO) {
        // 订单通常不允许用户直接Update，通常是修改状态（取消/支付）
        // 这里仅做示例修复
        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderDTO.getOrderId())
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }

        BeanUtils.copyProperties(orderDTO, order);
        return orderRepository.save(order);
    }

    @Override
    public Order queryOrder(Integer orderId) {
        Integer currentUserId = UserContext.getUserId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查：只有订单主人才看详情
        if (!order.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        return order;
    }

    // 🔒 新增：查我的订单（实现之前的讨论）
    @Override
    public List<Order> findMyOrders() {
        Integer currentUserId = UserContext.getUserId();
        return orderRepository.findByUserId(currentUserId);
    }

    @Override
    public List<Order> queryAllOrderBatch(List<Integer> userIdlist) {
        // 管理员接口，暂时保持原样
        return orderRepository.findAllById(userIdlist);
    }

    @Override
    public List<Order> findAllOrders() {
        // 管理员接口，暂时保持原样
        return orderRepository.findAll();
    }
}
