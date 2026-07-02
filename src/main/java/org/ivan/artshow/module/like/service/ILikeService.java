package org.ivan.artshow.module.like.service;

import org.ivan.artshow.module.like.pojo.Like;
import org.ivan.artshow.module.like.pojo.dto.LikeDTO;
import org.springframework.stereotype.Service;

@Service
/**
 * ILikeService - 业务服务接口
 *
 * <p>ILikeService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ILikeService {
    public Like addLike(LikeDTO like);
    public void deleteLike(Integer id);
    public Like updateLike(LikeDTO like);
    public Like queryLike(Integer id);
}
