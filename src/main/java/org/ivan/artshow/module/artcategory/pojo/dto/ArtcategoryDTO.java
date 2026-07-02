package org.ivan.artshow.module.artcategory.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ArtcategoryDTO - 数据传输对象
 *
 * <p>ArtcategoryDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class ArtcategoryDTO {
    @Getter
    @Setter
    private Integer categoryId;

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    private String iconUrl;
}
