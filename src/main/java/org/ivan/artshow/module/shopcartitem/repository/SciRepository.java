package org.ivan.artshow.module.shopcartitem.repository;

import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * SciRepository - 数据访问接口
 *
 * <p>SciRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface SciRepository extends JpaRepository<Sci, Integer> {
    /**
     * 查询用户的购物车
     * @param userId 用户ID
     * @return 购物车项列表
     */
    List<Sci> findByUserId(Integer userId);

    /**
     * 批量查询购物车项（用于结算）
     * @param cartItemIds 购物车项ID列表
     * @return 购物车项列表
     */
    List<Sci> findByCartItemIdIn(List<Integer> cartItemIds);
}
