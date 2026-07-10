package org.ivan.artshow.module.product.pojo.dto;

import java.math.BigDecimal;

/**
 * ProductDTO - 数据传输对象
 *
 * ProductDTO用于前后端数据传输和验证
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record ProductDTO(
        Long id,
        Integer sellerId,
        String name,
        BigDecimal price,
        Integer stock,
        String imageUrl,
        String description,
        Boolean isCertified
) {
    public Long getId() { return id; }
    public Integer getSellerId() { return sellerId; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public Integer getStock() { return stock; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }
    public Boolean getIsCertified() { return isCertified; }
}
