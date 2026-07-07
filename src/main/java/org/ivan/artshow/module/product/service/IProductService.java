package org.ivan.artshow.module.product.service;

import org.ivan.artshow.module.product.pojo.dto.ProductDTO;
import org.ivan.artshow.module.product.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;


/**
  IProductService - 业务服务接口
  @author Ivan Horn
 * @since 1.0.0
 */
@Service
public interface IProductService {
    Product addOneProduct(ProductDTO product);
    void addProducts(Iterable<Product> productList);

    void deleteOneProduct(Long productId);
    void deleteProducts(Iterable<Long> productIdList);

    Product updateOneProduct(ProductDTO product);

    Product queryOneProduct(Long productId);

    List<Product> findAllProducts();

    /**
     * 检查商品库存是否充足
     * @param productId 商品ID
     * @param quantity 需要的数量
     * @return true表示库存充足
     */
    boolean checkStock(Long productId, Integer quantity);

    /**
     * 扣减商品库存（原子操作）
     * @param productId 商品ID
     * @param quantity 扣减数量
     * @return true表示扣减成功，false表示库存不足
     */
    boolean deductStock(Long productId, Integer quantity);

    /**
     * 恢复商品库存（取消订单时使用）
     * @param productId 商品ID
     * @param quantity 恢复数量
     */
    void restoreStock(Long productId, Integer quantity);
}
