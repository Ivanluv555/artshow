package org.ivan.artshow.module.product.service;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.product.pojo.dto.ProductDTO;
import org.ivan.artshow.module.product.pojo.Product;
import org.ivan.artshow.module.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * ProductService - 业务服务实现类
 *
 * <p>ProductService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addOneProduct(ProductDTO product) {
        Product nProduct = new Product();
        BeanUtils.copyProperties(product, nProduct);
        return productRepository.save(nProduct);
    }

    @Override
    public Product updateOneProduct(ProductDTO product) {
        Integer productId = product.getId();
        Product nProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("没有这个东西" + productId));
        BeanUtils.copyProperties(product, nProduct);
        return productRepository.save(nProduct);
    }

    @Override
    public Product queryOneProduct(Integer productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public void deleteOneProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void deleteProducts(Iterable<Integer> productIdList) {
        productRepository.deleteAllById(productIdList);
    }

    @Override
    public void addProducts(Iterable<Product> productList) {
        productRepository.saveAll(productList);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}

