package org.ivan.artshow.module.shopcartitem.service;

import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.ivan.artshow.module.shopcartitem.pojo.dto.CartItemWithProductDTO;
import org.ivan.artshow.module.shopcartitem.pojo.dto.SciDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

//本文件的全名是shopping_cart_item
@Service
/**
 * ISciService - 业务服务接口
 *
 * <p>ISciService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ISciService {
    public Sci addSci(SciDTO sci);
    public void deleteSci(Long cartItemId);
    public Sci updateSci(SciDTO Sci);
    public Sci querySci(Long cartItemId);
    public List<Sci> queryAllSciBatch(List<Long> userIdList);

    /**
     * 查询当前用户的购物车（按创建时间倒序）
     * @return 购物车项列表
     */
    List<Sci> findMyCart();

    /**
     * 分页查询当前用户的购物车，并关联商品信息（按创建时间倒序）
     * @param page 页码（从0开始）
     * @param size 每页大小
     * @return 购物车项分页结果，包含商品信息
     */
    Page<CartItemWithProductDTO> findMyCartWithProducts(int page, int size);

    /**
     * 批量删除购物车项
     * @param cartItemIds 购物车项ID列表
     */
    void batchDeleteCartItems(List<Long> cartItemIds);

    /**
     * 清空当前用户的购物车
     */
    void clearMyCart();

    /**
     * 获取购物车商品种类数
     * @return 商品种类数
     */
    int getCartItemCount();
}
