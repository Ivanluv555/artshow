package org.ivan.artshow.module.product.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.product.pojo.dto.ProductDTO;
import org.ivan.artshow.module.product.pojo.Product;
import org.ivan.artshow.module.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
/**
 * ProductController - 控制器
 *
 * <p>ProductController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping
    public Result<Product> addOneProduct(@RequestBody ProductDTO product) {
        Product nProduct = productService.addOneProduct(product);
        return Result.success(nProduct);
    }

    @PostMapping("/batch")
    public Result<Product> addProducts(@RequestBody Iterable<Product> product) {
        productService.addProducts(product);
        return Result.success(null);
    }

    @DeleteMapping
    public Result<Product> deleteOneProduct(@RequestParam Integer productId) {
        productService.deleteOneProduct(productId);
        return Result.success(null);
    }

    @DeleteMapping("/dbatch")
    public Result<Product> deleteProducts(@RequestBody Iterable<Integer> productId) {
        productService.deleteProducts(productId);
        return Result.success(null);
    }

    @PutMapping
    public Result<Product> updateOneProduct(@RequestBody ProductDTO product) {
        Product nProduct = productService.updateOneProduct(product);
        return Result.success(nProduct);
    }

    @GetMapping
    public Result<Product> queryOneProduct(@RequestParam Integer productId) {
        Product nProduct = productService.queryOneProduct(productId);
        return Result.success(nProduct);
    }

    @GetMapping("/list") // GET /product/list
    public Result<List<Product>> listProducts() {
        return Result.success(productService.findAllProducts());
    }
}
