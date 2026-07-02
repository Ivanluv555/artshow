package org.ivan.artshow.module.artsubcategory.repository;

import org.ivan.artshow.module.artsubcategory.pojo.Artsubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ArtsubRepository - 数据访问接口
 *
 * <p>ArtsubRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ArtsubRepository extends JpaRepository<Artsubcategory, Integer> {
}
