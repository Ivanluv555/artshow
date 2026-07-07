package org.ivan.artshow.module.address.repository;

import org.ivan.artshow.module.address.pojo.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AddRepository - 数据访问接口
 *
 * <p>AddRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface AddRepository extends JpaRepository<Address, Long> {
}
