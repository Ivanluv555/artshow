package org.ivan.artshow.module.merchant.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.merchant.pojo.Merchant;
import org.ivan.artshow.module.merchant.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * MerchantService - 商家业务服务实现类
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class MerchantService implements IMerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    @Transactional
    public Merchant createMerchant(Merchant merchant) {
        if (merchant == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Long userId = merchant.getUserId();
        if (userId == null) {
            throw new BizException(ResultCodes.INVALID_PARAM, "用户ID不能为空");
        }

        // 检查用户是否已有商家身份
        if (merchantRepository.existsByUserId(userId)) {
            throw new BizException(ResultCodes.INVALID_PARAM, "该用户已拥有商家身份");
        }

        // 设置默认值
        if (merchant.getStatus() == null) {
            merchant.setStatus("pending");
        }
        if (merchant.getTotalProducts() == null) {
            merchant.setTotalProducts(0);
        }

        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant queryMerchant(Long merchantId) {
        if (merchantId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return merchantRepository.findById(merchantId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "商家不存在"));
    }

    @Override
    public Merchant queryMerchantByUserId(Long userId) {
        if (userId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return merchantRepository.findByUserId(userId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "该用户不是商家"));
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Merchant updateMerchant(Merchant merchant) {
        if (merchant == null || merchant.getId() == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Merchant existing = merchantRepository.findById(merchant.getId())
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "商家不存在"));

        // 权限检查：只有商家本人或管理员可以修改
        Long currentUserId = UserContext.getUserId();
        if (!existing.getUserId().equals(currentUserId)
                && !UserContext.hasRole(org.ivan.artshow.common.auth.UserRole.ADMIN)) {
            throw new BizException(ResultCodes.FORBIDDEN, "无权修改此商家信息");
        }

        // 更新字段（不允许修改userId）
        if (merchant.getShopName() != null)
            existing.setShopName(merchant.getShopName());
        if (merchant.getShopLogo() != null)
            existing.setShopLogo(merchant.getShopLogo());
        if (merchant.getShopDescription() != null)
            existing.setShopDescription(merchant.getShopDescription());
        if (merchant.getBusinessLicense() != null)
            existing.setBusinessLicense(merchant.getBusinessLicense());

        return merchantRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteMerchant(Long merchantId) {
        if (merchantId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "商家不存在"));

        // 权限检查：只有管理员可以删除商家身份
        if (!UserContext.hasRole(org.ivan.artshow.common.auth.UserRole.ADMIN)) {
            throw new BizException(ResultCodes.FORBIDDEN, "无权删除商家身份");
        }

        merchantRepository.deleteById(merchantId);
    }
}
