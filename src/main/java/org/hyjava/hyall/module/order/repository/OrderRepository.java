package org.hyjava.hyall.module.order.repository;
import org.hyjava.hyall.common.core.result.Result;

import org.hyjava.hyall.module.order.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(Integer userId);
}
