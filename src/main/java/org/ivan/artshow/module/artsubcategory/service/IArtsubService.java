package org.ivan.artshow.module.artsubcategory.service;

import org.ivan.artshow.module.artsubcategory.pojo.Artsubcategory;
import org.ivan.artshow.module.artsubcategory.pojo.dto.ArtsubcategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * IArtsubService - 业务服务接口
 *
 * <p>IArtsubService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IArtsubService {
    public Artsubcategory addArtSub(ArtsubcategoryDTO artSubCategory);
    public Artsubcategory updateArtSub(ArtsubcategoryDTO artSubCategory);
    public void deleteArtSub(Long id);
    public Artsubcategory queryArtSub(Long id);
    List<Artsubcategory> findAllSubCategories();
}
