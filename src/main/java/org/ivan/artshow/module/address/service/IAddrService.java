package org.ivan.artshow.module.address.service;

import org.ivan.artshow.module.address.pojo.Address;
import org.ivan.artshow.module.address.pojo.dto.AddressDTO;

/**
 * 地址服务接口
 * 定义地址管理的业务方法
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IAddrService {
    /**
     * 添加用户地址
     * @param address 地址DTO对象
     * @return 新增的地址对象
     */
    Address addUserAddress(AddressDTO address);

    /**
     * 删除用户地址（权限校验：只能删除自己的地址）
     * @param addressId 地址ID
     */
    void deleteUserAddress(Integer addressId);

    /**
     * 更新用户地址（权限校验：只能修改自己的地址）
     * @param address 地址DTO对象
     * @return 更新后的地址对象
     */
    Address updateUserAddress(AddressDTO address);

    /**
     * 查询用户地址（权限校验：只能查询自己的地址）
     * @param addressId 地址ID
     * @return 地址对象
     */
    Address queryUserAddress(Integer addressId);
}
