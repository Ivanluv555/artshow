package org.ivan.artshow.module.orderitem.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

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
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_id", insertable = false, updatable = false)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private org.ivan.artshow.module.order.pojo.Order order;

    @Column(name="product_id")
    private Long productId;

    @Column(name="course_id")
    private Long courseId;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="price_at_purchase")
    private BigDecimal priceAtPurchase;

    @Column(name="product_name_snapshot")
    private String productNameSnapshot;

    @Column(name="product_image_snapshot")
    private String productImageSnapshot;

    public Orderitem() {
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public org.ivan.artshow.module.order.pojo.Order getOrder() {
        return order;
    }

    public void setOrder(org.ivan.artshow.module.order.pojo.Order order) {
        this.order = order;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
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
