package org.ivan.artshow.module.artsubcategory.pojo.dto;

/**
 * ArtsubcategoryDTO - 数据传输对象
 *
 * <p>ArtsubcategoryDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record ArtsubcategoryDTO(
        Long subCateId,
        Long categoryId,
        String name,
        String coverImageUrl,
        String introduction,
        String history,
        String features,
        String culturalMeaning
) {
    public Long getSubCateId() { return subCateId; }
    public Long getCategoryId() { return categoryId; }
    public String getName() { return name; }
    public String getCoverImageUrl() { return coverImageUrl; }
    public String getIntroduction() { return introduction; }
    public String getHistory() { return history; }
    public String getFeatures() { return features; }
    public String getCulturalMeaning() { return culturalMeaning; }
}
