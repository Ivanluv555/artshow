package org.ivan.artshow.module.product.repository;

import org.ivan.artshow.module.product.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductRepository - 数据访问接口
 * ProductRepository继承JpaRepository，不需要写注解咯
 * 提供数据库操作方法
 *
 * @author Ivan Horn
 * @since 1.0.0
 */

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
