package org.ivan.artshow.module.like.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * LikeDTO - 数据传输对象
 *
 * <p>LikeDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class LikeDTO {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private int postId;

    @Getter
    @Setter
    private int userId;

    @Getter
    @Setter
    private Date createdAt;
}
