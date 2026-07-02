package org.ivan.artshow.module.order.repository;

import org.ivan.artshow.module.order.pojo.OrderShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderShippingAddressRepository - 数据访问接口
 *
 * <p>OrderShippingAddressRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface OrderShippingAddressRepository extends JpaRepository<OrderShippingAddress, Integer> {
}
