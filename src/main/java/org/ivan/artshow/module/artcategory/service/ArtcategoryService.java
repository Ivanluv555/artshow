package org.ivan.artshow.module.artcategory.service;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.artcategory.pojo.Artcategory;
import org.ivan.artshow.module.artcategory.pojo.dto.ArtcategoryDTO;
import org.ivan.artshow.module.artcategory.repository.ArtcategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ArtcategoryService - 业务服务实现类
 *
 * <p>ArtcategoryService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class ArtcategoryService implements IArtcategoryService {
    private final ArtcategoryRepository artCateRepository;

    public ArtcategoryService(ArtcategoryRepository artCateRepository) {
        this.artCateRepository = artCateRepository;
    }

    @Override
    public Artcategory addCate(ArtcategoryDTO artcategory) {
        Artcategory artCategory = new Artcategory();
        BeanUtils.copyProperties(artcategory,artCategory);
        return artCateRepository.save(artCategory);
    }

    @Override
    public Artcategory updateCate(ArtcategoryDTO artcategory) {
        Integer artcateId = artcategory.getCategoryId();
        Artcategory artCategory = artCateRepository.findById(artcateId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(artcategory,artCategory);
        return artCateRepository.save(artCategory);
    }

    @Override
    public void deleteCate(Integer cateId) {
        artCateRepository.deleteById(cateId);
    }

    @Override
    public Artcategory queryCate(Integer cateId) {
        return artCateRepository.findById(cateId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Artcategory> findAllCategories() {
        return artCateRepository.findAll();
    }
}
