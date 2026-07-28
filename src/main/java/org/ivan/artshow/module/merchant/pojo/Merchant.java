package org.ivan.artshow.module.merchant.pojo;

import jakarta.persistence.*;
import org.ivan.artshow.common.config.SnowflakeId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Merchant - 商家实体类
 *
 * <p>
 * 商家身份信息，关联到user表，一个用户只能有一个商家身份。
 * </p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @Column(name = "merchant_id")
    @SnowflakeId
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_logo")
    private String shopLogo;

    @Column(name = "shop_description")
    private String shopDescription;

    @Column(name = "business_license")
    private String businessLicense;

    @Column(name = "total_products")
    private Integer totalProducts;

    @Column(name = "total_sales")
    private BigDecimal totalSales;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "status")
    private String status;

    @Column(name = "approved_at")
    private Date approvedAt;

    @Column(name = "created_at")
    private Date createdAt;

    public Merchant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public Integer getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Integer totalProducts) {
        this.totalProducts = totalProducts;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
