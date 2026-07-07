package org.ivan.artshow.module.address.pojo.dto;

import org.hibernate.validator.constraints.Length;

/**
 * AddressDTO - Data Transfer Object
 *
 * <p>AddressDTO is used for frontend-backend data transfer and validation.</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record AddressDTO(
        Long addressId,
        Integer userId,
        String recipientName,
        @Length(min = 11, max = 11, message = "Phone number must be 11 digits")
        String phone,
        String region,
        String detailAddress,
        Boolean isDefault
) {
    public Long getAddressId() { return addressId; }
    public Integer getUserId() { return userId; }
    public String getRecipientName() { return recipientName; }
    public String getPhone() { return phone; }
    public String getRegion() { return region; }
    public String getDetailAddress() { return detailAddress; }
    public Boolean getIsDefault() { return isDefault; }
}
