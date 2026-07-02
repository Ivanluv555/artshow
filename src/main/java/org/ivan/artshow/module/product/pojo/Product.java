package org.ivan.artshow.module.product.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
/**
 * Product - 实体类
 *
 * Product对应数据库表，使用JPA注解映射表结构
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name="product_id")
    private Integer id;
    @Column(name = "seller_id")
    @Getter
    @Setter
    private Integer sellerId;
    @Column(name="name")
    @Getter
    @Setter
    private String name;
    @Column(name="price")
    @Getter
    @Setter
    private Double price;
    @Column(name="stock")
    @Getter
    @Setter
    private Integer stock;
    @Column(name="cover_image_url")
    @Getter
    @Setter
    private String imageUrl;
    @Column(name="description")
    @Getter
    @Setter
    private String description;
    @Column(name = "is_certified")
    @Getter
    @Setter
    private Boolean isCertified;
}

