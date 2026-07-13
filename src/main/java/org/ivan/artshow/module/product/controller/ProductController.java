package org.ivan.artshow.module.product.controller;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.product.pojo.dto.ProductDTO;
import org.ivan.artshow.module.product.pojo.Product;
import org.ivan.artshow.module.product.service.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController - 控制器
 *
 * <p>ProductController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // 添加商品 - 讲师或管理员
    @RequireRole({UserRole.INSTRUCTOR, UserRole.ADMIN})
    @PostMapping
    public Result<Product> addOneProduct(@RequestBody ProductDTO product) {
        Product nProduct = productService.addOneProduct(product);
        return Result.success(nProduct);
    }

    // 批量添加商品 - 讲师或管理员
    @RequireRole({UserRole.INSTRUCTOR, UserRole.ADMIN})
    @PostMapping("/batch")
    public Result<Product> addProducts(@RequestBody Iterable<Product> product) {
        productService.addProducts(product);
        return Result.success(null);
    }

    // 删除商品 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @DeleteMapping
    public Result<Product> deleteOneProduct(@RequestParam Long productId) {
        productService.deleteOneProduct(productId);
        return Result.success(null);
    }

    // 批量删除商品 - 仅管理员
    @RequireRole(UserRole.ADMIN)
    @DeleteMapping("/dbatch")
    public Result<Product> deleteProducts(@RequestBody Iterable<Long> productId) {
        productService.deleteProducts(productId);
        return Result.success(null);
    }

    // 更新商品 - 讲师或管理员
    @RequireRole({UserRole.INSTRUCTOR, UserRole.ADMIN})
    @PutMapping
    public Result<Product> updateOneProduct(@RequestBody ProductDTO product) {
        Product nProduct = productService.updateOneProduct(product);
        return Result.success(nProduct);
    }

    // 查询商品详情 - 公开
    @Public("商品详情")
    @GetMapping
    public Result<Product> queryOneProduct(@RequestParam Long productId) {
        Product nProduct = productService.queryOneProduct(productId);
        return Result.success(nProduct);
    }

    // 查询商品列表 - 公开
    @Public("商品列表")
    @GetMapping("/list") // GET /product/list
    public Result<List<Product>> listProducts() {
        return Result.success(productService.findAllProducts());
    }
}
