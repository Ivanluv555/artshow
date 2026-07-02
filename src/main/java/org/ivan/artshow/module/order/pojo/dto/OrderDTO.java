package org.ivan.artshow.module.order.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * OrderDTO - 数据传输对象
 *
 * <p>OrderDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class OrderDTO {
    @Getter @Setter
    private Integer orderId;
    @Getter @Setter
    private String orderNumber;
    @Getter @Setter
    private Integer userId;
    @Getter @Setter
    private Double totalPrice;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private Date createdAt;

    // 新增：前端下单必须传这个地址ID
    @Getter @Setter
    private Integer addressId;
}
