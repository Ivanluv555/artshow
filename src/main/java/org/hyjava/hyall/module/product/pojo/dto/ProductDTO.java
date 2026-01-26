package org.hyjava.hyall.module.product.pojo.dto;
import org.hyjava.hyall.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

public class ProductDTO {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer sellerId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private Integer stock;

    @Getter
    @Setter
    private String imageUrl;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Boolean isCertified;
}
