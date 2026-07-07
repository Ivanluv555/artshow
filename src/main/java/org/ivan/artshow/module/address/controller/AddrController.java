package org.ivan.artshow.module.address.controller;

import org.ivan.artshow.common.core.result.Result;
import org.ivan.artshow.module.address.pojo.Address;
import org.ivan.artshow.module.address.pojo.dto.AddressDTO;
import org.ivan.artshow.module.address.service.IAddrService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 地址管理控制器
 * 提供用户地址的增删改查接口
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/address")
public class AddrController {
    private final IAddrService addrService;

    public AddrController(IAddrService addrService) {
        this.addrService = addrService;
    }

    /**
     * 添加用户地址
     * @param address 地址信息DTO
     * @return 新增的地址对象
     */
    @PostMapping
    public Result<Address> addUserAddress(@RequestBody @Validated AddressDTO address) {
        Address naddress = addrService.addUserAddress(address);
        return Result.success(naddress);
    }

    /**
     * 删除用户地址
     * @param addressId 地址ID
     */
    @DeleteMapping
    public void deleteUserAddress(@RequestParam Long addressId) {
        addrService.deleteUserAddress(addressId);
    }

    /**
     * 更新用户地址
     * @param address 地址信息DTO
     * @return 更新后的地址对象
     */
    @PutMapping
    public Result<Address> updateUserAddress(@RequestBody @Validated AddressDTO address) {
        Address naddress = addrService.updateUserAddress(address);
        return Result.success(naddress);
    }

    /**
     * 查询用户地址
     * @param addressId 地址ID
     * @return 地址对象
     */
    @GetMapping
    public Result<Address> queryUserAddress(@RequestParam Long addressId) {
        Address naddress = addrService.queryUserAddress(addressId);
        return Result.success(naddress);
    }
}
