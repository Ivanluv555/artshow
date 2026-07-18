package org.ivan.artshow.module.address.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ivan.artshow.common.auth.*;
import org.ivan.artshow.common.core.result.Result;
import org.ivan.artshow.module.address.pojo.Address;
import org.ivan.artshow.module.address.pojo.dto.AddressDTO;
import org.ivan.artshow.module.address.service.IAddrService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址管理控制器
 * 提供用户地址的增删改查接口
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/address")
@Tag(name = "Address Management", description = "User address management endpoints")
public class AddrController {
    private final IAddrService addrService;

    public AddrController(IAddrService addrService) {
        this.addrService = addrService;
    }

    /**
     * 添加用户地址 - 需要登录
     * @param address 地址信息DTO
     * @return 新增的地址对象
     */
    @PostMapping
    @Operation(summary = "Add Address", description = "Add a new address for current user")
    @SecurityRequirement(name = "BearerAuth")
    public Result<Address> addUserAddress(@RequestBody @Validated AddressDTO address) {
        Address naddress = addrService.addUserAddress(address);
        return Result.success(naddress);
    }

    /**
     * 删除用户地址 - 需要登录
     * @param addressId 地址ID
     */
    @DeleteMapping
    @Operation(summary = "Delete Address", description = "Delete an address by ID")
    @SecurityRequirement(name = "BearerAuth")
    public void deleteUserAddress(@RequestParam @Parameter(description = "Address ID") Long addressId) {
        addrService.deleteUserAddress(addressId);
    }

    /**
     * 更新用户地址 - 需要登录
     * @param address 地址信息DTO
     * @return 更新后的地址对象
     */
    @PutMapping
    @Operation(summary = "Update Address", description = "Update an existing address")
    @SecurityRequirement(name = "BearerAuth")
    public Result<Address> updateUserAddress(@RequestBody @Validated AddressDTO address) {
        Address naddress = addrService.updateUserAddress(address);
        return Result.success(naddress);
    }

    /**
     * 查询用户地址 - 需要登录
     * @param addressId 地址ID
     * @return 地址对象
     */
    @GetMapping
    @Operation(summary = "Query Address", description = "Query address details by ID")
    @SecurityRequirement(name = "BearerAuth")
    public Result<Address> queryUserAddress(@RequestParam @Parameter(description = "Address ID") Long addressId) {
        Address naddress = addrService.queryUserAddress(addressId);
        return Result.success(naddress);
    }

    /**
     * 设置默认地址 - 需要登录
     * @param addressId 地址ID
     * @return 设置后的地址对象
     */
    @PutMapping("/default")
    @Operation(summary = "Set Default Address",
               description = "Set an address as default, other addresses will be set as non-default")
    @SecurityRequirement(name = "BearerAuth")
    public Result<Address> setDefaultAddress(@RequestParam @Parameter(description = "Address ID") Long addressId) {
        Address address = addrService.setDefaultAddress(addressId);
        return Result.success(address);
    }

    /**
     * 查询我的所有地址 - 需要登录
     * @return 地址列表
     */
    @GetMapping("/my")
    @Operation(summary = "Query My Addresses", description = "Query all addresses of current user")
    @SecurityRequirement(name = "BearerAuth")
    public Result<List<Address>> queryMyAddresses() {
        List<Address> addresses = addrService.queryMyAddresses();
        return Result.success(addresses);
    }

    /**
     * 查询我的默认地址 - 需要登录
     * @return 默认地址
     */
    @GetMapping("/my/default")
    @Operation(summary = "Query My Default Address", description = "Query the default address of current user")
    @SecurityRequirement(name = "BearerAuth")
    public Result<Address> queryMyDefaultAddress() {
        Address address = addrService.queryMyDefaultAddress();
        return Result.success(address);
    }
}
