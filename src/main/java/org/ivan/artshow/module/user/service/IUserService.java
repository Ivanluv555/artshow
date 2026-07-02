package org.ivan.artshow.module.user.service;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.user.pojo.User;
import org.ivan.artshow.module.user.pojo.dto.UserDTO;
import java.util.List;

/**
 * 用户服务接口
 *
 * <p>定义用户模块的业务逻辑方法，包括用户的增删改查、登录等功能。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>用户管理：添加、删除、更新、查询用户信息</li>
 *   <li>批量查询：根据用户ID列表批量查询用户</li>
 *   <li>用户认证：处理用户登录，返回JWT令牌</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IUserService {
    /**
     * 添加用户
     *
     * @param user 用户DTO对象
     * @return 新增的用户对象
     */
    User addUser(UserDTO user);

    /**
     * 删除用户
     *
     * @param UserId 用户ID
     */
    void deleteUser(Integer UserId);

    /**
     * 更新用户信息
     *
     * @param User 用户DTO对象
     * @return 更新后的用户对象
     */
    User updateUser(UserDTO User);

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象
     */
    User queryUser(Integer userId);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    List<User> findAllUsers();

    /**
     * 根据用户ID列表批量查询用户
     *
     * @param userIdList 用户ID列表
     * @return 用户列表
     */
    List<User> queryAllUser(List<Integer> userIdList);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return JWT令牌
     */
    String login(String username, String password);
}
