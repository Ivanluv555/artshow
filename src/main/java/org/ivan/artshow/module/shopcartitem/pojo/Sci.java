package org.ivan.artshow.module.shopcartitem.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "shopping_cart_item")
/**
 * Sci - 实体类
 *
 * <p>Sci对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Sci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "cart_item_id")
    private Integer cartItemId;
    @Getter
    @Setter
    @Column(name = "user_id")
    private Integer userId;
    @Getter
    @Setter
    @Column(name = "product_id")
    private Integer productId;
    @Getter
    @Setter
    @Column(name = "quantity")
    private Integer quantity;
    @Getter
    @Setter
    @Column(name = "created_at")
    private Date createdAt;
}
