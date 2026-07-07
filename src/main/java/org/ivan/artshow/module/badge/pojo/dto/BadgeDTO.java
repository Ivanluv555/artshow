package org.ivan.artshow.module.badge.pojo.dto;

/**
 * BadgeDTO - 数据传输对象
 *
 * <p>BadgeDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record BadgeDTO(
        Long badgeId,
        String badgeName,
        String badgeDescription,
        String badgeImage
) {
    public Long getBadgeId() { return badgeId; }
    public String getBadgeName() { return badgeName; }
    public String getBadgeDescription() { return badgeDescription; }
    public String getBadgeImage() { return badgeImage; }
}
