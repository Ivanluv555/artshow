package org.ivan.artshow.module.address.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_address")
/**
 * Address - 实体类
 *
 * <p>Address对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    @Getter
    @Setter
    private Integer addressId;

    @Column(name = "user_id")
    @Getter
    @Setter
    private Integer userId;

    @Column(name = "recipient_name")
    @Getter
    @Setter
    private String recipientName;

    @Column(name = "phone")
    @Getter
    @Setter
    private String phone;

    @Column(name = "region")
    @Getter
    @Setter
    private String region;

    @Column(name = "detail")
    @Getter
    @Setter
    private String detailAddress;

    @Column(name = "is_default")
    @Getter
    @Setter
    private Boolean isDefault;

    @Column(name = "created_at")
    @Getter
    @Setter
    private Date createdAt;

}
