package org.ivan.artshow.module.address.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * AddressDTO - 数据传输对象
 *
 * <p>AddressDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class AddressDTO {
    @Getter
    @Setter
    private Integer addressId;
    @Getter
    @Setter
    private Integer userId;
    @Getter
    @Setter
    private String recipientName;
    @Getter
    @Setter
    @Length(min = 11, max = 11, message = "手机号必须为11位")
    private String phone;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private String detailAddress;
    @Getter
    @Setter
    private Boolean isDefault;
}
