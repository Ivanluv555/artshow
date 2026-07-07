package org.ivan.artshow.module.artcategory.repository;

import org.ivan.artshow.module.artcategory.pojo.Artcategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ArtcategoryRepository - 数据访问接口
 *
 * <p>ArtcategoryRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ArtcategoryRepository extends JpaRepository<Artcategory, Long> {
}
