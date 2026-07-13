package org.ivan.artshow.module.user.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.ivan.artshow.common.auth.Public;
import org.ivan.artshow.common.auth.RequireRole;
import org.ivan.artshow.common.auth.UserRole;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.user.pojo.User;
import org.ivan.artshow.module.user.pojo.dto.UserDTO;
import org.ivan.artshow.module.user.service.IUserService;
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
@Tag(name = "User Management", description = "User registration, login, and information management endpoints")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Add user / Register
     *
     * @param user User DTO object
     * @return Newly created user information
     */
    @Public("用户注册")
    @PostMapping
    @Operation(summary = "Register User", description = "Create new user account (public endpoint)")
    public Result<User> addUser(@RequestBody @Validated UserDTO user) {
        User nuser = userService.addUser(user);
        return Result.success(nuser);
    }

    /**
     * Delete user (Admin only)
     *
     * @param userId User ID
     */
    @RequireRole(UserRole.ADMIN)
    @DeleteMapping
    @Operation(summary = "Delete User", description = "Delete user by user ID (Admin only)")
    @SecurityRequirement(name = "BearerAuth")
    public void deleteUser(@RequestParam @Parameter(description = "User ID", required = true) Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * Update user information
     *
     * @param user User DTO object
     * @return Updated user information
     */
    @PutMapping
    @Operation(summary = "Update User", description = "Update user information")
    @SecurityRequirement(name = "BearerAuth")
    public Result<User> updateUser(@RequestBody @Validated UserDTO user) {
        User nuser = userService.updateUser(user);
        return Result.success(nuser);
    }

    /**
     * Query single user
     *
     * @param userId User ID
     * @return User information
     */
    @GetMapping
    @Operation(summary = "Query User", description = "Query user details by user ID")
    @SecurityRequirement(name = "BearerAuth")
    public Result<User> queryUser(@RequestParam @Parameter(description = "User ID", required = true) Long userId) {
        User nuser = userService.queryUser(userId);
        return Result.success(nuser);
    }

    /**
     * Batch query users
     *
     * @param userIds User ID list
     * @return User list
     */
    @PostMapping("/batch")
    @Operation(summary = "Batch Query Users", description = "Batch query user information by user ID list")
    @SecurityRequirement(name = "BearerAuth")
    public Result<List<User>> queryAllUser(@RequestBody List<Long> userIds) {
        List<User> nLUser = userService.queryAllUser(userIds);
        return Result.success(nLUser);
    }

    /**
     * User login (no authentication required)
     *
     * @param userDTO DTO containing username and password
     * @return JWT token
     */
    @Public("用户登录")
    @PostMapping("/login")
    @Operation(
            summary = "User Login",
            description = "Login with username and password, returns JWT token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login successful, returns JWT token",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Invalid username or password")
            }
    )
    public Result<String> login(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Login credentials, including username and password",
            required = true
    ) UserDTO userDTO) {
        String token = userService.login(userDTO.getUserName(), userDTO.getPassword());
        return Result.success(token);
    }

    /**
     * Query all users (Admin only)
     *
     * @return User list
     */
    @RequireRole(UserRole.ADMIN)
    @GetMapping("/list")
    @Operation(summary = "Query All Users", description = "Get all users in the system (Admin only)")
    @SecurityRequirement(name = "BearerAuth")
    public Result<List<User>> listUsers() {
        return Result.success(userService.findAllUsers());
    }
}