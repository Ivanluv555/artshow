package org.ivan.artshow.module.orderitem.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
/**
 * Orderitem - 实体类
 *
 * <p>Orderitem对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    @Getter
    @Setter
    private Integer orderItemId;

    @Column(name = "order_id")
    @Getter
    @Setter
    private Integer orderId;
    @Column(name="product_id")
    @Getter
    @Setter
    private Integer productId;
    @Column(name="quantity")
    @Getter
    @Setter
    private Integer quantity;
    @Column(name="price_at_purchase")
    @Getter
    @Setter
    private Double priceAtPurchase;
    @Column(name="product_name_snapshot")
    @Getter
    @Setter
    private String productNameSnapshot;
    @Column(name="product_image_snapshot")
    @Getter
    @Setter
    private String productImageSnapshot;
}
