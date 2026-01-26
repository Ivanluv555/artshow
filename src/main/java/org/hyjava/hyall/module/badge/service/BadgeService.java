package org.hyjava.hyall.module.badge.service;
import org.hyjava.hyall.common.core.result.Result;

import org.hyjava.hyall.common.core.resultcode.ResultCodes;
import org.hyjava.hyall.common.exception.BizException;
import org.hyjava.hyall.module.badge.pojo.Badge;
import org.hyjava.hyall.module.badge.pojo.dto.BadgeDTO;
import org.hyjava.hyall.module.badge.repository.BadgeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService implements IBadgeService{
    @Autowired
    BadgeRepository badgeRepository;

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
