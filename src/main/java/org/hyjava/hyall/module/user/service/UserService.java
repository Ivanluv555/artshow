package org.hyjava.hyall.module.user.service;

import org.hyjava.hyall.common.auth.UserContext;
import org.hyjava.hyall.common.core.resultcode.ResultCodes;
import org.hyjava.hyall.common.exception.BizException;
import org.hyjava.hyall.common.utils.JwtUtils;
import org.hyjava.hyall.module.user.pojo.User;
import org.hyjava.hyall.module.user.pojo.dto.UserDTO;
import org.hyjava.hyall.module.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(UserDTO user) {
        // 注册逻辑保持不变
        User nUser = new User();
        BeanUtils.copyProperties(user, nUser);
        return userRepository.save(nUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        // 通常只有管理员能删用户，或者用户注销自己
        Integer currentUserId = UserContext.getUserId();
        if (!currentUserId.equals(userId)) {
            throw new BizException(ResultCodes.UNAUTH); // 只允许注销自己
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        // 🔒 核心修复：完全忽略前端传来的 userId，只用 Token 里的 userId
        Integer currentUserId = UserContext.getUserId();

        User nUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 只更新允许修改的字段（防止恶意修改用户名或余额等敏感信息）
        if (userDTO.getNickName() != null) nUser.setNickName(userDTO.getNickName());
        if (userDTO.getUserAvatar() != null) nUser.setUserAvatar(userDTO.getUserAvatar());
        if (userDTO.getUserBio() != null) nUser.setUserBio(userDTO.getUserBio());
        // 如果允许改密码，最好做旧密码校验逻辑

        return userRepository.save(nUser);
    }

    @Override
    public User queryUser(Integer userId) {
        // 查询公开资料，不需要权限检查
        return userRepository.findById(userId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> queryAllUser(List<Integer> userIdList) {
        return userRepository.findAllById(userIdList);
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new BizException(ResultCodes.NOTFOUND);
        }
        if (!user.getPassword().equals(password)) {
            throw new BizException(ResultCodes.ERROR);
        }
        return JwtUtils.createToken(user.getUserId());
    }
}