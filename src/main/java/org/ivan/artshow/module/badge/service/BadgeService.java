package org.ivan.artshow.module.badge.service;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.badge.pojo.Badge;
import org.ivan.artshow.module.badge.pojo.dto.BadgeDTO;
import org.ivan.artshow.module.badge.repository.BadgeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BadgeService - 业务服务实现类
 *
 * <p>BadgeService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class BadgeService implements IBadgeService{
    private final BadgeRepository badgeRepository;

    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    @Override
    public Badge addBadge(BadgeDTO badge){
        Badge nbadge = new Badge();
        BeanUtils.copyProperties(badge,nbadge);
        return badgeRepository.save(nbadge);
    }

    @Override
    public void deleteBadge(Integer badgeId){
        badgeRepository.deleteById(badgeId);
    }

    @Override
    public Badge updateBadge(BadgeDTO Badge){
        Integer BadgeId = Badge.getBadgeId();
        Badge nbadge = badgeRepository.findById(BadgeId).orElseThrow(()-> new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(Badge,nbadge);
        return badgeRepository.save(nbadge);
    }
    @Override
    public Badge queryBadge(Integer badgeId) {
        return badgeRepository.findById(badgeId).orElseThrow(()-> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Badge> findAllBadges() {
        return badgeRepository.findAll();
    }
}
