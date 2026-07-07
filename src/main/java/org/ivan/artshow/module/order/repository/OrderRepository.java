package org.ivan.artshow.module.order.repository;

import org.ivan.artshow.module.order.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * OrderRepository - 数据访问接口
 *
 * <p>OrderRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
}
