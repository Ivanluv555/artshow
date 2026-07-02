package org.ivan.artshow.module.artsubcategory.service;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.artsubcategory.pojo.dto.ArtsubcategoryDTO;
import org.ivan.artshow.module.artsubcategory.repository.ArtsubRepository;
import org.ivan.artshow.module.artsubcategory.pojo.Artsubcategory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ArtsubService - 业务服务实现类
 *
 * <p>ArtsubService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class ArtsubService implements IArtsubService {
    private final ArtsubRepository artSubRepository;

    public ArtsubService(ArtsubRepository artSubRepository) {
        this.artSubRepository = artSubRepository;
    }

    @Override
    public Artsubcategory addArtSub(ArtsubcategoryDTO artSubCategory) {
        Artsubcategory artsub = new Artsubcategory();
        BeanUtils.copyProperties(artSubCategory,artsub);
        return artsub;
    }

    @Override
    public Artsubcategory updateArtSub(ArtsubcategoryDTO artsub) {
        Integer artsubId = artsub.getSubCateId();
        Artsubcategory nartsub = artSubRepository.findById(artsubId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(artsub,artsub);
        return nartsub;
    }

    @Override
    public void deleteArtSub(Integer artsubId) {
        artSubRepository.deleteById(artsubId);
    }

    @Override
    public Artsubcategory queryArtSub(Integer artsubId) {
        return artSubRepository.findById(artsubId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Artsubcategory> findAllSubCategories() {
        return artSubRepository.findAll();
    }
}
