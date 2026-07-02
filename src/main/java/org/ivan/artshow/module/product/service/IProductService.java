package org.ivan.artshow.module.product.service;

import org.ivan.artshow.module.product.pojo.dto.ProductDTO;
import org.ivan.artshow.module.product.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * IProductService - 业务服务接口
 *
 * <p>IProductService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IProductService {
    public Product addOneProduct(ProductDTO product);
    public void addProducts(Iterable<Product> productList);

    public void deleteOneProduct(Integer productId);
    public void deleteProducts(Iterable<Integer> productIdList);

    public Product updateOneProduct(ProductDTO product);

    public Product queryOneProduct(Integer productId);

    List<Product> findAllProducts();
}
