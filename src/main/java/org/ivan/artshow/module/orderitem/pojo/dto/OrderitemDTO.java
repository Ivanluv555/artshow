package org.ivan.artshow.module.orderitem.pojo.dto;

/**
 * OrderitemDTO - 数据传输对象
 *
 * <p>OrderitemDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record OrderitemDTO(
        Long orderItemId,
        Long orderId,
        Integer productId,
        Integer quantity,
        Double priceAtPurchase,
        String productNameSnapshot,
        String productImageSnapshot
) {
    public Long getOrderItemId() { return orderItemId; }
    public Long getOrderId() { return orderId; }
    public Integer getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    public Double getPriceAtPurchase() { return priceAtPurchase; }
    public String getProductNameSnapshot() { return productNameSnapshot; }
    public String getProductImageSnapshot() { return productImageSnapshot; }
}
