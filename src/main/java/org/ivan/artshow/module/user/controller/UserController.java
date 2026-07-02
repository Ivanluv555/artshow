package org.ivan.artshow.module.user.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.user.pojo.User;
import org.ivan.artshow.module.user.pojo.dto.UserDTO;
import org.ivan.artshow.module.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * <p>处理用户相关的HTTP请求，提供用户管理和认证的RESTful API接口。</p>
 *
 * <p>主要接口：</p>
 * <ul>
 *   <li>POST /user - 添加用户</li>
 *   <li>DELETE /user - 删除用户</li>
 *   <li>PUT /user - 更新用户</li>
 *   <li>GET /user - 查询单个用户</li>
 *   <li>GET /user/list - 查询所有用户</li>
 *   <li>POST /user/batch - 批量查询用户</li>
 *   <li>POST /user/login - 用户登录（无需认证）</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    IUserService userService;

    /**
     * 添加用户
     *
     * @param user 用户DTO对象
     * @return 新增的用户信息
     */
    @PostMapping
    public Result<User> addUser(@RequestBody @Validated UserDTO user) {
        User nuser = userService.addUser(user);
        return Result.success(nuser);
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    @DeleteMapping
    public void deleteUser(@RequestParam Integer userId) {
        userService.deleteUser(userId);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户DTO对象
     * @return 更新后的用户信息
     */
    @PutMapping
    public Result<User> updateUser(@RequestBody @Validated UserDTO user) {
        User nuser = userService.updateUser(user);
        return Result.success(nuser);
    }

    /**
     * 查询单个用户
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping
    public Result<User> queryUser(@RequestParam Integer userId) {
        User nuser = userService.queryUser(userId);
        return Result.success(nuser);
    }

    /**
     * 批量查询用户
     *
     * @param userIds 用户ID列表
     * @return 用户列表
     */
    @PostMapping("/batch")
    public Result<List<User>> queryAllUser(@RequestBody List<Integer> userIds) {
        List<User> nLUser = userService.queryAllUser(userIds);
        return Result.success(nLUser);
    }

    /**
     * 用户登录（无需认证）
     *
     * @param userDTO 包含用户名和密码的DTO
     * @return JWT令牌
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        String token = userService.login(userDTO.getUserName(), userDTO.getPassword());
        return Result.success(token);
    }

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @GetMapping("/list")
    public Result<List<User>> listUsers() {
        return Result.success(userService.findAllUsers());
    }
}