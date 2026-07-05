package org.ivan.artshow.module.order.pojo.dto;

import java.util.Date;

/**
 * OrderDTO - 数据传输对象
 *
 * <p>OrderDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record OrderDTO(
        Integer orderId,
        String orderNumber,
        Integer userId,
        Double totalPrice,
        String status,
        Date createdAt,
        // 新增：前端下单必须传这个地址ID
        Integer addressId
) {
    public Integer getOrderId() { return orderId; }
    public String getOrderNumber() { return orderNumber; }
    public Integer getUserId() { return userId; }
    public Double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public Date getCreatedAt() { return createdAt; }
    public Integer getAddressId() { return addressId; }
}
