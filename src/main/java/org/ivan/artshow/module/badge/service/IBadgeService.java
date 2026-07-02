package org.ivan.artshow.module.badge.service;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.badge.pojo.Badge;
import org.ivan.artshow.module.badge.pojo.dto.BadgeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * IBadgeService - 业务服务接口
 *
 * <p>IBadgeService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IBadgeService {
    public Badge addBadge(BadgeDTO badge);
    public void deleteBadge(Integer badgeId);
    public Badge updateBadge(BadgeDTO badge);
    public Badge queryBadge(Integer badgeId);
    List<Badge> findAllBadges();
}
