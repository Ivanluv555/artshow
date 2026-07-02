package org.ivan.artshow.module.badge.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "badge")
/**
 * Badge - 实体类
 *
 * <p>Badge对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    @Getter
    @Setter
    private Integer badgeId;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;
    @Column(name="description")
    @Getter
    @Setter
    private String description;
    @Column(name="icon_url")
    @Getter
    @Setter
    private String iconUrl;

}
