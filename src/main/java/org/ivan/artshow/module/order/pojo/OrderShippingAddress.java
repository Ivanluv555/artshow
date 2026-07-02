package org.ivan.artshow.module.order.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_shipping_address")
/**
 * OrderShippingAddress - 实体类
 *
 * <p>OrderShippingAddress对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class OrderShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_address_id")
    @Getter @Setter
    private Integer shippingAddressId;

    @Column(name = "order_id")
    @Getter @Setter
    private Integer orderId;

    @Column(name = "recipient_name")
    @Getter @Setter
    private String recipientName;

    @Column(name = "phone")
    @Getter @Setter
    private String phone;

    @Column(name = "region")
    @Getter @Setter
    private String region;

    @Column(name = "detail")
    @Getter @Setter
    private String detail;
}
