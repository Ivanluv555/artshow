package org.ivan.artshow.module.user.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.common.utils.JwtUtils;
import org.ivan.artshow.common.utils.PasswordUtils;
import org.ivan.artshow.module.user.pojo.User;
import org.ivan.artshow.module.user.pojo.dto.UserDTO;
import org.ivan.artshow.module.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 * 实现用户注册、登录、个人信息管理等业务逻辑
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(UserDTO user) {
        if (user == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 检查用户名是否已存在
        if (user.getUserName() != null) {
            User existingUser = userRepository.findByUserName(user.getUserName());
            if (existingUser != null) {
                throw new BizException(ResultCodes.INVALID_PARAM, "用户名已存在");
            }
        }

        User nUser = new User();
        BeanUtils.copyProperties(user, nUser);

        // 加密密码
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encryptedPassword = PasswordUtils.encrypt(user.getPassword());
            nUser.setPassword(encryptedPassword);
        }

        // 新注册用户默认角色为USER
        if (nUser.getRole() == null || nUser.getRole().isEmpty()) {
            nUser.setRole("USER");
        }
        return userRepository.save(nUser);
    }

    @Override
    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();
        if (!currentUserId.equals(userId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();
        User nUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        if (userDTO.getNickName() != null) nUser.setNickName(userDTO.getNickName());
        if (userDTO.getUserAvatar() != null) nUser.setUserAvatar(userDTO.getUserAvatar());
        if (userDTO.getUserBio() != null) nUser.setUserBio(userDTO.getUserBio());
        return userRepository.save(nUser);
    }

    @Override
    public User queryUser(Long userId) {
        if (userId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> queryAllUser(List<Long> userIdList) {
        if (userIdList == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return userRepository.findAllById(userIdList);
    }

    @Override
    public String login(String username, String password) {
        if (username == null || password == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new BizException(ResultCodes.NOTFOUND, "用户名或密码错误");
        }

        // 使用BCrypt验证密码
        if (!PasswordUtils.matches(password, user.getPassword())) {
            throw new BizException(ResultCodes.ERROR, "用户名或密码错误");
        }

        // 生成包含角色的JWT token
        return JwtUtils.createToken(user.getUserId(), user.getRole());
    }
}
