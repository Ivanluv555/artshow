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

    /**
     * 查询用户的所有订单，按创建时间倒序
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);

    /**
     * 查询用户指定状态的订单，按创建时间倒序
     *
     * @param userId 用户ID
     * @param status 订单状态
     * @return 订单列表
     */
    List<Order> findByUserIdAndStatusOrderByCreatedAtDesc(Long userId, String status);

    /**
     * 查询用户的所有订单（旧方法，保留兼容性）
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> findByUserId(Long userId);
}
