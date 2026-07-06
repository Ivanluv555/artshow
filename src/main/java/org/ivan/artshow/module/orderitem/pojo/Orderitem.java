package org.ivan.artshow.module.orderitem.pojo;

import jakarta.persistence.*;

/**
 * Orderitem - 实体类
 *
 * <p>Orderitem对应数据库表，使用JPA注解映射表结构。</p>
 * <p>订单项可以关联商品(product_id)或课程(course_id)，二者至少有一个不为空。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "order_item")
public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name="product_id")
    private Integer productId;

    @Column(name="course_id")
    private Integer courseId;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="price_at_purchase")
    private Double priceAtPurchase;

    @Column(name="product_name_snapshot")
    private String productNameSnapshot;

    @Column(name="product_image_snapshot")
    private String productImageSnapshot;

    public Orderitem() {
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(Double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public String getProductNameSnapshot() {
        return productNameSnapshot;
    }

    public void setProductNameSnapshot(String productNameSnapshot) {
        this.productNameSnapshot = productNameSnapshot;
    }

    public String getProductImageSnapshot() {
        return productImageSnapshot;
    }

    public void setProductImageSnapshot(String productImageSnapshot) {
        this.productImageSnapshot = productImageSnapshot;
    }
}
