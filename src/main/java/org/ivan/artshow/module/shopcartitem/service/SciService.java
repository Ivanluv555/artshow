package org.ivan.artshow.module.shopcartitem.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.product.pojo.Product;
import org.ivan.artshow.module.product.repository.ProductRepository;
import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.ivan.artshow.module.shopcartitem.pojo.dto.CartItemWithProductDTO;
import org.ivan.artshow.module.shopcartitem.pojo.dto.SciDTO;
import org.ivan.artshow.module.shopcartitem.repository.SciRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SciService - 业务服务实现类
 *
 * <p>SciService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class SciService implements ISciService {
    private final SciRepository sciRepository;
    private final ProductRepository productRepository;

    public SciService(SciRepository sciRepository, ProductRepository productRepository) {
        this.sciRepository = sciRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Sci addSci(SciDTO sciDTO) {
        if (sciDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();

        Sci nSci = new Sci();
        BeanUtils.copyProperties(sciDTO, nSci);
        nSci.setUserId(currentUserId); // 🔒 强制绑定当前用户
        return sciRepository.save(nSci);
    }

    @Override
    public void deleteSci(Long cartItemId) {
        if (cartItemId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();

        Sci sci = sciRepository.findById(cartItemId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查
        if (!sci.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }

        sciRepository.deleteById(cartItemId);
    }

    @Override
    public Sci updateSci(SciDTO sciDTO) {
        if (sciDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();
        Long cartItemId = sciDTO.getCartItemId();
        if (cartItemId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Sci oldSci = sciRepository.findById(cartItemId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查
        if (!oldSci.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }

        BeanUtils.copyProperties(sciDTO, oldSci);
        oldSci.setUserId(currentUserId); // 保持所有权不变
        return sciRepository.save(oldSci);
    }

    @Override
    public Sci querySci(Long cartItemId) {
        if (cartItemId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();
        Sci sci = sciRepository.findById(cartItemId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查
        if (!sci.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        return sci;
    }

    @Override
    public List<Sci> queryAllSciBatch(List<Long> userIdList) {
        if (userIdList == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        // 这个接口原本是给管理员用的。
        // 如果给普通用户用，必须改写为：return sciRepository.findByUserId(UserContext.getUserId());
        return sciRepository.findAllById(userIdList);
    }

    @Override
    public List<Sci> findMyCart() {
        Long currentUserId = UserContext.getUserId();
        return sciRepository.findByUserIdOrderByCreatedAtDesc(currentUserId);
    }

    @Override
    public Page<CartItemWithProductDTO> findMyCartWithProducts(int page, int size) {
        Long currentUserId = UserContext.getUserId();

        // 分页查询购物车项（按创建时间倒序）
        Pageable pageable = PageRequest.of(page, size);
        Page<Sci> cartItemPage = sciRepository.findByUserIdOrderByCreatedAtDesc(currentUserId, pageable);

        // 提取所有商品ID
        List<Long> productIds = cartItemPage.getContent().stream()
                .map(Sci::getProductId)
                .collect(Collectors.toList());

        // 批量查询商品信息（半懒加载）
        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        // 组装DTO
        return cartItemPage.map(cartItem -> {
            CartItemWithProductDTO dto = new CartItemWithProductDTO();
            dto.setCartItemId(cartItem.getCartItemId());
            dto.setUserId(cartItem.getUserId());
            dto.setProductId(cartItem.getProductId());
            dto.setQuantity(cartItem.getQuantity());
            dto.setCreatedAt(cartItem.getCreatedAt());
            dto.setProduct(productMap.get(cartItem.getProductId()));
            return dto;
        });
    }

    @Override
    @Transactional
    public void batchDeleteCartItems(List<Long> cartItemIds) {
        if (cartItemIds == null || cartItemIds.isEmpty()) {
            throw new BizException(ResultCodes.INVALID_PARAM, "购物车项ID列表不能为空");
        }

        Long currentUserId = UserContext.getUserId();

        // 查询要删除的购物车项
        List<Sci> cartItems = sciRepository.findAllById(cartItemIds);

        // 权限检查：只能删除自己的购物车项
        for (Sci item : cartItems) {
            if (!item.getUserId().equals(currentUserId)) {
                throw new BizException(ResultCodes.FORBIDDEN, "无权删除其他用户的购物车项");
            }
        }

        // 批量删除
        sciRepository.deleteAllById(cartItemIds);
    }

    @Override
    public void clearMyCart() {
        Long currentUserId = UserContext.getUserId();
        List<Sci> myCart = sciRepository.findByUserId(currentUserId);
        if (!myCart.isEmpty()) {
            sciRepository.deleteAll(myCart);
        }
    }

    @Override
    public int getCartItemCount() {
        Long currentUserId = UserContext.getUserId();
        List<Sci> myCart = sciRepository.findByUserId(currentUserId);
        return myCart.size();
    }
}
