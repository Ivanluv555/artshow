package org.ivan.artshow.module.order.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * OrderShippingAddress - 实体类
 *
 * <p>OrderShippingAddress对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "order_shipping_address")
public class OrderShippingAddress {
    @Id
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name = "shipping_address_id")
    private Long shippingAddressId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "region")
    private String region;

    @Column(name = "detail")
    private String detail;

    public OrderShippingAddress() {
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
