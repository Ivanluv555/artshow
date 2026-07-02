package org.ivan.artshow.module.user.pojo.dto;
import org.ivan.artshow.common.core.result.Result;

import lombok.Getter;
import lombok.Setter;

/**
 * UserDTO - 数据传输对象
 *
 * <p>UserDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class UserDTO {
    @Getter
    @Setter
    private Integer userId;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String nickName;
    @Getter
    @Setter
    private String userAvatar;
    @Getter
    @Setter
    private String userBio;
}
