package org.ivan.artshow.module.address.repository;

import org.ivan.artshow.module.address.pojo.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * AddRepository - 数据访问接口
 * <p>AddRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface AddRepository extends JpaRepository<Address, Long> {

    /**
     * 查询用户的所有地址
     *
     * @param userId 用户ID
     * @return 地址列表
     */
    List<Address> findByUserId(Long userId);

    /**
     * 查询用户的默认地址
     *
     * @param userId 用户ID
     * @return 默认地址（如果存在）
     */
    Optional<Address> findByUserIdAndIsDefault(Long userId, Boolean isDefault);

    /**
     * 将用户的所有地址设置为非默认
     *
     * @param userId 用户ID
     * @return 更新的记录数
     */
    @Modifying
    @Query("UPDATE Address a SET a.isDefault = false WHERE a.userId = :userId")
    int clearDefaultByUserId(@Param("userId") Long userId);

    /**
     * 统计用户的地址数量
     *
     * @param userId 用户ID
     * @return 地址数量
     */
    long countByUserId(Long userId);
}
