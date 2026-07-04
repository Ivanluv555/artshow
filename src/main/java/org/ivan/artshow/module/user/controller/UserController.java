package org.ivan.artshow.module.user.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
     * Add user
     *
     * @param user User DTO object
     * @return Newly created user information
     */
    @PostMapping
    @Operation(summary = "Add User", description = "Create new user, requires admin permission")
    @SecurityRequirement(name = "BearerAuth")
    public Result<User> addUser(@RequestBody @Validated UserDTO user) {
        User nuser = userService.addUser(user);
        return Result.success(nuser);
    }

    /**
     * Delete user
     *
     * @param userId User ID
     */
    @DeleteMapping
    @Operation(summary = "Delete User", description = "Delete user by user ID")
    @SecurityRequirement(name = "BearerAuth")
    public void deleteUser(@RequestParam @Parameter(description = "User ID", required = true) Integer userId) {
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
    public Result<User> queryUser(@RequestParam @Parameter(description = "User ID", required = true) Integer userId) {
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
    public Result<List<User>> queryAllUser(@RequestBody List<Integer> userIds) {
        List<User> nLUser = userService.queryAllUser(userIds);
        return Result.success(nLUser);
    }

    /**
     * User login (no authentication required)
     *
     * @param userDTO DTO containing username and password
     * @return JWT token
     */
    @PostMapping("/login")
    @Operation(
            summary = "User Login",
            description = "Login with username and password, returns JWT token.\n\n" +
                    "**Usage Steps:**\n" +
                    "1. Call this endpoint to get token\n" +
                    "2. Copy the returned token string\n" +
                    "3. Click 🔓 \"Authorize\" button in the top right corner\n" +
                    "4. Paste the token in the popup (no need to add Bearer prefix)\n" +
                    "5. Click \"Authorize\" button to complete authorization\n" +
                    "6. All authenticated endpoints will automatically carry the token",
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
     * Query all users
     *
     * @return User list
     */
    @GetMapping("/list")
    @Operation(summary = "Query All Users", description = "Get all users in the system")
    @SecurityRequirement(name = "BearerAuth")
    public Result<List<User>> listUsers() {
        return Result.success(userService.findAllUsers());
    }
}