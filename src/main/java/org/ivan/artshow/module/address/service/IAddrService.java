package org.ivan.artshow.module.address.service;

import org.ivan.artshow.module.address.pojo.Address;
import org.ivan.artshow.module.address.pojo.dto.AddressDTO;

import java.util.List;

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
    void deleteUserAddress(Long addressId);

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
    Address queryUserAddress(Long addressId);

    /**
     * 设置默认地址
     * 将指定地址设为默认，其他地址设为非默认
     *
     * @param addressId 地址ID
     * @return 设置后的地址对象
     */
    Address setDefaultAddress(Long addressId);

    /**
     * 查询当前用户的所有地址
     *
     * @return 地址列表
     */
    List<Address> queryMyAddresses();

    /**
     * 查询当前用户的默认地址
     *
     * @return 默认地址（如果存在）
     */
    Address queryMyDefaultAddress();
}
