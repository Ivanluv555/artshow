package org.ivan.artshow.module.badge.repository;

import org.ivan.artshow.module.badge.pojo.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BadgeRepository - 数据访问接口
 *
 * <p>BadgeRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface BadgeRepository extends JpaRepository<Badge,Long> {
}
