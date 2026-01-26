package org.hyjava.hyall.module.address.service;

import org.hyjava.hyall.common.auth.UserContext;
import org.hyjava.hyall.common.core.resultcode.ResultCodes;
import org.hyjava.hyall.common.exception.BizException;
import org.hyjava.hyall.module.address.pojo.Address;
import org.hyjava.hyall.module.address.pojo.dto.AddressDTO;
import org.hyjava.hyall.module.address.repository.AddRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddrService implements IAddrService {
    @Autowired
    AddRepository addRepository;

    @Override
    public Address addUserAddress(AddressDTO addressDTO) {
        // 1. 强制使用当前登录用户的 ID，防止帮别人加地址
        Integer currentUserId = UserContext.getUserId();

        Address nAddress = new Address();
        BeanUtils.copyProperties(addressDTO, nAddress);
        nAddress.setUserId(currentUserId); // 🔒 关键：绑定当前用户
        return addRepository.save(nAddress);
    }

    @Override
    public void deleteUserAddress(Integer addressId) {
        Integer currentUserId = UserContext.getUserId();

        // 1. 先查出来
        Address address = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 2. 🔒 检查权限：只有主人能删
        if (!address.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }

        addRepository.deleteById(addressId);
    }

    @Override
    public Address updateUserAddress(AddressDTO addressDTO) {
        Integer currentUserId = UserContext.getUserId();
        Integer addressId = addressDTO.getAddressId();

        // 1. 先查旧数据
        Address oldAddress = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 2. 🔒 检查权限：只有主人能改
        if (!oldAddress.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }

        // 3. 更新数据 (userId 保持不变)
        BeanUtils.copyProperties(addressDTO, oldAddress);
        oldAddress.setUserId(currentUserId); // 再次确保 ID 不被篡改

        return addRepository.save(oldAddress);
    }

    @Override
    public Address queryUserAddress(Integer addressId) {
        Integer currentUserId = UserContext.getUserId();

        Address address = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 3. 🔒 检查权限：防止偷窥别人地址
        if (!address.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        return address;
    }
}