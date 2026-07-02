package org.ivan.artshow.module.artcategory.service;

import org.ivan.artshow.module.artcategory.pojo.Artcategory;
import org.ivan.artshow.module.artcategory.pojo.dto.ArtcategoryDTO;

import java.util.List;

/**
 * IArtcategoryService - 业务服务接口
 *
 * <p>IArtcategoryService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IArtcategoryService {
    public Artcategory addCate(ArtcategoryDTO artcategory);
    public Artcategory updateCate(ArtcategoryDTO artcategory);
    public void deleteCate(Integer cateId);
    public Artcategory queryCate(Integer cateId);
    List<Artcategory> findAllCategories();
}
