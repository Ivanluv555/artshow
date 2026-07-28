package org.ivan.artshow.module.merchant.repository;

import org.ivan.artshow.module.merchant.pojo.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * MerchantRepository - 商家数据访问接口
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    /**
     * 根据用户ID查找商家
     *
     * @param userId 用户ID
     * @return 商家信息
     */
    Optional<Merchant> findByUserId(Long userId);

    /**
     * 检查用户是否已有商家身份
     *
     * @param userId 用户ID
     * @return 是否存在
     */
    boolean existsByUserId(Long userId);
}
