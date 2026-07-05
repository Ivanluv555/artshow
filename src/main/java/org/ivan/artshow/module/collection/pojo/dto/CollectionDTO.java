package org.ivan.artshow.module.collection.pojo.dto;

import java.util.Date;

/**
 * CollectionDTO - 数据传输对象
 *
 * <p>CollectionDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record CollectionDTO(
        Integer collectionId,
        Integer postId,
        Integer userId,
        Date createAt
) {
    public Integer getCollectionId() { return collectionId; }
    public Integer getPostId() { return postId; }
    public Integer getUserId() { return userId; }
    public Date getCreateAt() { return createAt; }
}
