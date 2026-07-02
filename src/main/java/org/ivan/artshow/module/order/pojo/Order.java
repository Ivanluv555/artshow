package org.ivan.artshow.module.order.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="`order`")
/**
 * Order - 实体类
 *
 * <p>Order对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name="order_id")
    private Integer orderId;
    @Column(name="order_number")
    @Getter
    @Setter
    private String orderNumber;
    @Column(name="user_id")
    @Getter
    @Setter
    private Integer userId;
    @Column(name="total_price")
    @Getter
    @Setter
    private Double totalPrice;
    @Column(name="status")
    @Getter
    @Setter
    private String status;
    @Column(name="created_at")
    @Getter
    @Setter
    private Date createdAt;


}
