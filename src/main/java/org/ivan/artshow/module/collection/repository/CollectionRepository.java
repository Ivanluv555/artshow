package org.ivan.artshow.module.collection.repository;

import org.ivan.artshow.module.collection.pojo.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CollectionRepository - 数据访问接口
 *
 * <p>CollectionRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface CollectionRepository extends JpaRepository<Collection,Integer> {
}
