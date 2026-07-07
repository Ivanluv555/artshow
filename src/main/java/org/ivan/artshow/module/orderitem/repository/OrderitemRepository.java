package org.ivan.artshow.module.orderitem.repository;

import org.ivan.artshow.module.orderitem.pojo.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * OrderitemRepository - 数据访问接口
 *
 * <p>OrderitemRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface OrderitemRepository extends JpaRepository<Orderitem, Long> {
    /**
     * 查询订单的所有订单项
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<Orderitem> findByOrderId(Long orderId);

    /**
     * 检查用户是否已购买某个课程（订单已支付）
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return 是否已购买
     */
    @Query("SELECT COUNT(oi) > 0 FROM Orderitem oi " +
           "JOIN oi.order o " +
           "WHERE o.userId = :userId " +
           "AND oi.courseId = :courseId " +
           "AND o.status IN ('paid', 'shipped', 'completed')")
    boolean existsPaidCourseByUserIdAndCourseId(@Param("userId") Long userId,
                                                 @Param("courseId") Long courseId);

    /**
     * 查询用户购买的所有课程订单项（已支付）
     * @param userId 用户ID
     * @return 课程订单项列表
     */
    @Query("SELECT oi FROM Orderitem oi " +
           "JOIN oi.order o " +
           "WHERE o.userId = :userId " +
           "AND oi.courseId IS NOT NULL " +
           "AND o.status IN ('paid', 'shipped', 'completed')")
    List<Orderitem> findPaidCoursesByUserId(@Param("userId") Long userId);
}
