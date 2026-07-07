package org.ivan.artshow.module.address.pojo;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Address - 实体类
 *
 * <p>Address对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "user_address")
public class Address {
    @Id
    @GeneratedValue(generator = "snowflake")
    @org.hibernate.annotations.GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "region")
    private String region;

    @Column(name = "detail")
    private String detailAddress;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "created_at")
    private Date createdAt;

    public Address() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
