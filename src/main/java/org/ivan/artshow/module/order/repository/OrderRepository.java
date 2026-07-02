package org.ivan.artshow.module.order.repository;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.order.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * OrderRepository - 数据访问接口
 *
 * <p>OrderRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(Integer userId);
}
