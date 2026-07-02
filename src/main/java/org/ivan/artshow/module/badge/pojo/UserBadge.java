package org.ivan.artshow.module.badge.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "user_badge")
/**
 * UserBadge - 实体类
 *
 * <p>UserBadge对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class UserBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_badge_id")
    @Getter @Setter
    private Integer userBadgeId;

    @Column(name = "user_id")
    @Getter @Setter
    private Integer userId;

    @Column(name = "badge_id")
    @Getter @Setter
    private Integer badgeId;

    @Column(name = "earned_at")
    @Getter @Setter
    private Date earnedAt;
}
