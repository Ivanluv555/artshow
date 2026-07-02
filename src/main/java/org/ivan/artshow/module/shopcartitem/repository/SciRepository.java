package org.ivan.artshow.module.shopcartitem.repository;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SciRepository - 数据访问接口
 *
 * <p>SciRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface SciRepository extends JpaRepository<Sci, Integer> {
}
