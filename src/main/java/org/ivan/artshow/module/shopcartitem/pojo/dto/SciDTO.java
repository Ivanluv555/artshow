package org.ivan.artshow.module.shopcartitem.pojo.dto;
import org.ivan.artshow.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

/**
 * SciDTO - 数据传输对象
 *
 * <p>SciDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class SciDTO {
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
