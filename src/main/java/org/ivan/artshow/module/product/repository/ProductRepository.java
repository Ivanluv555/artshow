package org.ivan.artshow.module.product.repository;

import org.ivan.artshow.module.product.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * ProductRepository - 数据访问接口
 * ProductRepository继承JpaRepository，不需要写注解咯
 * 提供数据库操作方法
 *
 * @author Ivan Horn
 * @since 1.0.0
 */

public interface ProductRepository extends JpaRepository<Product,Long> {
    /**
     * 扣减商品库存（原子操作，防止超卖）
     * @param productId 商品ID
     * @param quantity 扣减数量
     * @return 影响的行数（0表示库存不足或商品不存在）
     */
    @Modifying
    @Query("UPDATE Product p SET p.stock = p.stock - :quantity WHERE p.id = :productId AND p.stock >= :quantity")
    int deductStock(@Param("productId") Long productId, @Param("quantity") Integer quantity);

    /**
     * 恢复商品库存（取消订单时使用）
     * @param productId 商品ID
     * @param quantity 恢复数量
     * @return 影响的行数
     */
    @Modifying
    @Query("UPDATE Product p SET p.stock = p.stock + :quantity WHERE p.id = :productId")
    int restoreStock(@Param("productId") Long productId, @Param("quantity") Integer quantity);
}
