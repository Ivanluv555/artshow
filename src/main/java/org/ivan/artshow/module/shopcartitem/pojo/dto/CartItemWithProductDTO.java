package org.ivan.artshow.module.shopcartitem.pojo.dto;

import org.ivan.artshow.module.product.pojo.Product;

import java.util.Date;

/**
 * CartItemWithProductDTO - 购物车项关联商品信息DTO
 *
 * <p>用于返回购物车项及其关联的商品详细信息（半懒加载）</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class CartItemWithProductDTO {
    private Long cartItemId;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Date createdAt;

    // 关联的商品信息
    private Product product;

    public CartItemWithProductDTO() {
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
