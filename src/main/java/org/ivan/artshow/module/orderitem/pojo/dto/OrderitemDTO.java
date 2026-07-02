package org.ivan.artshow.module.orderitem.pojo.dto;
import org.ivan.artshow.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

/**
 * OrderitemDTO - 数据传输对象
 *
 * <p>OrderitemDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class OrderitemDTO {
    @Getter
    @Setter
    private Integer orderItemId;
    @Getter
    @Setter
    private Integer orderId;
    @Getter
    @Setter
    private Integer productId;
    @Getter
    @Setter
    private Integer quantity;
    @Getter
    @Setter
    private Double priceAtPurchase;
    @Getter
    @Setter
    private String productNameSnapshot;
    @Getter
    @Setter
    private String productImageSnapshot;

}
