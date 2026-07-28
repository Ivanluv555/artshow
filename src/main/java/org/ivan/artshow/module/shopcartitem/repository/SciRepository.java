package org.ivan.artshow.module.shopcartitem.repository;

import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface SciRepository extends JpaRepository<Sci, Long> {
    /**
     * 查询用户的购物车
     * @param userId 用户ID
     * @return 购物车项列表
     */
    List<Sci> findByUserId(Long userId);

    /**
     * 查询用户的购物车（按创建时间倒序）
     * @param userId 用户ID
     * @return 购物车项列表
     */
    List<Sci> findByUserIdOrderByCreatedAtDesc(Long userId);

    /**
     * 分页查询用户的购物车（按创建时间倒序）
     * @param userId 用户ID
     * @param pageable 分页参数
     * @return 购物车项分页结果
     */
    Page<Sci> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    /**
     * 批量查询购物车项（用于结算）
     * @param cartItemIds 购物车项ID列表
     * @return 购物车项列表
     */
    List<Sci> findByCartItemIdIn(List<Long> cartItemIds);
}
