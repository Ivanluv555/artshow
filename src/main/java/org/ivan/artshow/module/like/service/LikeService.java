package org.ivan.artshow.module.like.service;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.like.pojo.Like;
import org.ivan.artshow.module.like.pojo.dto.LikeDTO;
import org.ivan.artshow.module.like.repository.LikeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * LikeService - 业务服务实现类
 *
 * <p>LikeService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class LikeService implements ILikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Like addLike(LikeDTO like) {
        Like nlike = new Like();
        BeanUtils.copyProperties(like,nlike);
        return likeRepository.save(nlike);
    }

    @Override
    public Like updateLike(LikeDTO like) {
        Integer LikeID = like.getId();
        Like nlike = likeRepository.findById(LikeID).orElseThrow(() -> new RuntimeException("没这个东西" + LikeID));
        return likeRepository.save(nlike);
    }

    @Override
    public void deleteLike(Integer id) {
        likeRepository.deleteById(id);
    }

    @Override
    public Like queryLike(Integer id) {
        return likeRepository.findById(id).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }
}
