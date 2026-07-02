package org.ivan.artshow.module.badge.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * BadgeDTO - 数据传输对象
 *
 * <p>BadgeDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class BadgeDTO {
    @Getter
    @Setter
    private Integer badgeId;
    @Getter
    @Setter
    private String badgeName;
    @Getter
    @Setter
    private String badgeDescription;
    @Getter
    @Setter
    private String badgeImage;
}
