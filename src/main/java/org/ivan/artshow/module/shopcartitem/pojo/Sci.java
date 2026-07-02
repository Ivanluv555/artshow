package org.ivan.artshow.module.shopcartitem.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Integer cartItemId;
    @Getter
    @Setter
    private Integer userId;
    @Getter
    @Setter
    private Integer productId;
    @Getter
    @Setter
    private Integer quantity;
}
