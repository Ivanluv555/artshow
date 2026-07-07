package org.ivan.artshow.module.shopcartitem.pojo.dto;

/**
 * SciDTO - 数据传输对象
 *
 * <p>SciDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record SciDTO(
        Long cartItemId,
        Integer userId,
        Integer productId,
        Integer quantity
) {
    public Long getCartItemId() { return cartItemId; }
    public Integer getUserId() { return userId; }
    public Integer getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
}
