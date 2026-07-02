package org.ivan.artshow.module.product.pojo.dto;
import org.ivan.artshow.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

/**
 * ProductDTO - 数据传输对象
 *
 * <p>ProductDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
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
