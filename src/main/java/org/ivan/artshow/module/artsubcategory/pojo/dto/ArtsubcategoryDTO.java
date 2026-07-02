package org.ivan.artshow.module.artsubcategory.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ArtsubcategoryDTO - 数据传输对象
 *
 * <p>ArtsubcategoryDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class ArtsubcategoryDTO {
    @Getter
    @Setter
    private Integer subCateId;

    @Getter
    @Setter
    private Integer categoryId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String coverImageUrl;

    @Getter
    @Setter
    private String introduction;

    @Getter
    @Setter
    private String history;

    @Getter
    @Setter
    private String features;

    @Getter
    @Setter
    private String culturalMeaning;
}
