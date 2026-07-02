package org.ivan.artshow.module.orderitem.repository;

import org.ivan.artshow.module.orderitem.pojo.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderitemRepository - 数据访问接口
 *
 * <p>OrderitemRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface OrderitemRepository extends JpaRepository<Orderitem, Integer> {
}
