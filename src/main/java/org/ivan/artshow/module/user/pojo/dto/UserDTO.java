package org.ivan.artshow.module.user.pojo.dto;

/**
 * UserDTO - 数据传输对象
 *
 * <p>UserDTO用于前后端数据传输和验证。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public record UserDTO(
        Long userId,
        String userName,
        String password,
        String nickName,
        String userAvatar,
        String userBio
) {
    public Long getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public String getNickName() { return nickName; }
    public String getUserAvatar() { return userAvatar; }
    public String getUserBio() { return userBio; }
}
