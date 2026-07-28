package org.ivan.artshow.module.merchant.service;

import org.ivan.artshow.module.merchant.pojo.Merchant;

/**
 * IMerchantService - 商家服务接口
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IMerchantService {

    /**
     * 创建商家身份
     *
     * @param merchant 商家信息
     * @return 创建的商家
     */
    Merchant createMerchant(Merchant merchant);

    /**
     * 根据ID查询商家
     *
     * @param merchantId 商家ID
     * @return 商家信息
     */
    Merchant queryMerchant(Long merchantId);

    /**
     * 根据用户ID查询商家
     *
     * @param userId 用户ID
     * @return 商家信息
     */
    Merchant queryMerchantByUserId(Long userId);

    /**
     * 更新商家信息
     *
     * @param merchant 商家信息
     * @return 更新后的商家
     */
    Merchant updateMerchant(Merchant merchant);

    /**
     * 删除商家
     *
     * @param merchantId 商家ID
     */
    void deleteMerchant(Long merchantId);
}
