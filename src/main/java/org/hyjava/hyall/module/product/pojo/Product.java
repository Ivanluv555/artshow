package org.hyjava.hyall.module.product.pojo;
import org.hyjava.hyall.common.core.result.Result;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
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

