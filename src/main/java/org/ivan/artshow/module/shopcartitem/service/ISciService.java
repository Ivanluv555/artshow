package org.ivan.artshow.module.shopcartitem.service;

import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.ivan.artshow.module.shopcartitem.pojo.dto.SciDTO;
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
    public void deleteSci(Integer cartItemId);
    public Sci updateSci(SciDTO Sci);
    public Sci querySci(Integer cartItemId);
    public List<Sci> queryAllSciBatch(List<Integer> userIdList);

    /**
     * 查询当前用户的购物车
     * @return 购物车项列表
     */
    List<Sci> findMyCart();

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
