package org.ivan.artshow.module.product.service;

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
        Integer productId = product.getId();
        if (productId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Product nProduct = productRepository.findById(productId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(product, nProduct);
        return productRepository.save(nProduct);
    }

    @Override
    public Product queryOneProduct(Integer productId) {
        if (productId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return productRepository.findById(productId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public void deleteOneProduct(Integer productId) {
        if (productId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        productRepository.deleteById(productId);
    }

    @Override
    public void deleteProducts(Iterable<Integer> productIdList) {
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
    public boolean checkStock(Integer productId, Integer quantity) {
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
    public boolean deductStock(Integer productId, Integer quantity) {
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
    public void restoreStock(Integer productId, Integer quantity) {
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

