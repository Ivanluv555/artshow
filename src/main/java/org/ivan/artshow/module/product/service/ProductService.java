package org.ivan.artshow.module.product.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.product.pojo.dto.ProductDTO;
import org.ivan.artshow.module.product.pojo.Product;
import org.ivan.artshow.module.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProductService - 业务服务实现类
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addOneProduct(ProductDTO product) {
        if (product == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Product nProduct = new Product();
        BeanUtils.copyProperties(product, nProduct);
        return productRepository.save(nProduct);
    }

    @Override
    public Product updateOneProduct(ProductDTO product) {
        if (product == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long productId = product.getId();
        if (productId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 查询商品
        Product nProduct = productRepository.findById(productId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "商品不存在"));

        // 权限检查：只有卖家本人或管理员可以修改
        Long currentUserId = UserContext.getUserId();
        String currentUserRole = UserContext.getRole();

        if (!nProduct.getSellerId().equals(currentUserId) && !"ADMIN".equals(currentUserRole)) {
            throw new BizException(ResultCodes.FORBIDDEN, "无权修改此商品");
        }

        // 更新商品信息（不允许修改sellerId）
        if (product.getName() != null) nProduct.setName(product.getName());
        if (product.getPrice() != null) nProduct.setPrice(product.getPrice());
        if (product.getStock() != null) nProduct.setStock(product.getStock());
        if (product.getImageUrl() != null) nProduct.setImageUrl(product.getImageUrl());
        if (product.getDescription() != null) nProduct.setDescription(product.getDescription());
        if (product.getIsCertified() != null) nProduct.setIsCertified(product.getIsCertified());

        return productRepository.save(nProduct);
    }

    @Override
    public Product queryOneProduct(Long productId) {
        if (productId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return productRepository.findById(productId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public void deleteOneProduct(Long productId) {
        if (productId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 查询商品
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "商品不存在"));

        // 权限检查：只有卖家本人或管理员可以删除
        Long currentUserId = UserContext.getUserId();
        String currentUserRole = UserContext.getRole();

        if (!product.getSellerId().equals(currentUserId) && !"ADMIN".equals(currentUserRole)) {
            throw new BizException(ResultCodes.FORBIDDEN, "无权删除此商品");
        }

        productRepository.deleteById(productId);
    }

    @Override
    public void deleteProducts(Iterable<Long> productIdList) {
        if (productIdList == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        productRepository.deleteAllById(productIdList);
    }

    @Override
    public void addProducts(Iterable<Product> productList) {
        if (productList == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        productRepository.saveAll(productList);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean checkStock(Long productId, Integer quantity) {
        if (productId == null || quantity == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        if (quantity <= 0) {
            throw new BizException(ResultCodes.INVALID_PARAM, "购买数量必须大于0");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "商品不存在"));
        return product.getStock() != null && product.getStock() >= quantity;
    }

    @Override
    @Transactional
    public boolean deductStock(Long productId, Integer quantity) {
        if (productId == null || quantity == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        if (quantity <= 0) {
            throw new BizException(ResultCodes.INVALID_PARAM, "扣减数量必须大于0");
        }
        int affectedRows = productRepository.deductStock(productId, quantity);
        return affectedRows > 0;
    }

    @Override
    @Transactional
    public void restoreStock(Long productId, Integer quantity) {
        if (productId == null || quantity == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        if (quantity <= 0) {
            throw new BizException(ResultCodes.INVALID_PARAM, "恢复数量必须大于0");
        }
        int affectedRows = productRepository.restoreStock(productId, quantity);
        if (affectedRows == 0) {
            throw new BizException(ResultCodes.NOTFOUND, "商品不存在");
        }
    }
}

