package org.ivan.artshow.module.artcategory.pojo.dto;

/**
 * ArtcategoryDTO - 数据传输对象
 *
 * <p>ArtcategoryDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record ArtcategoryDTO(
        Long categoryId,
        String categoryName,
        String iconUrl
) {
    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public String getIconUrl() { return iconUrl; }
}
