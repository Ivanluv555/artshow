package org.ivan.artshow.module.collection.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * CollectionDTO - 数据传输对象
 *
 * <p>CollectionDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class CollectionDTO {
    @Getter
    @Setter
    private Integer collectionId;
    @Getter
    @Setter
    private Integer postId;
    @Getter
    @Setter
    private Integer userId;
    @Getter
    @Setter
    private Date createAt;
}
