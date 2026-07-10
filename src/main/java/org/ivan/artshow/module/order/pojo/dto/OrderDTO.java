package org.ivan.artshow.module.order.pojo.dto;

import java.math.BigDecimal;
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
        Long orderId,
        String orderNumber,
        Long userId,
        BigDecimal totalPrice,
        String status,
        Date createdAt,
        // 新增：前端下单必须传这个地址ID
        Long addressId
) {
    public Long getOrderId() { return orderId; }
    public String getOrderNumber() { return orderNumber; }
    public Long getUserId() { return userId; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public Date getCreatedAt() { return createdAt; }
    public Long getAddressId() { return addressId; }
}
