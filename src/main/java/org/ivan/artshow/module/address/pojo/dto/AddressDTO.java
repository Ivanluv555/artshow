package org.ivan.artshow.module.address.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * AddressDTO - Data Transfer Object
 *
 * <p>AddressDTO is used for frontend-backend data transfer and validation.</p>
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
    @Length(min = 11, max = 11, message = "Phone number must be 11 digits")
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
