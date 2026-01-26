package org.hyjava.hyall.module.artcategory.service;
import org.hyjava.hyall.common.core.result.Result;

import org.hyjava.hyall.common.core.resultcode.ResultCodes;
import org.hyjava.hyall.common.exception.BizException;
import org.hyjava.hyall.module.artcategory.pojo.Artcategory;
import org.hyjava.hyall.module.artcategory.pojo.dto.ArtcategoryDTO;
import org.hyjava.hyall.module.artcategory.repository.ArtcategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtcategoryService implements IArtcategoryService {
    @Autowired
    ArtcategoryRepository artCateRepository;

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
