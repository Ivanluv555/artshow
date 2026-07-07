package org.ivan.artshow.module.like.repository;

import org.ivan.artshow.module.like.pojo.Like;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * LikeRepository - 数据访问接口
 *
 * <p>LikeRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface LikeRepository extends JpaRepository<Like,Long> {
}
