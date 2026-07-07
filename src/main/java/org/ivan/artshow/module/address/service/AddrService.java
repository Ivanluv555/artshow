package org.ivan.artshow.module.address.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.address.pojo.Address;
import org.ivan.artshow.module.address.pojo.dto.AddressDTO;
import org.ivan.artshow.module.address.repository.AddRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 地址服务实现类
 * 实现地址管理的业务逻辑，包含权限校验
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class AddrService implements IAddrService {
    private final AddRepository addRepository;

    public AddrService(AddRepository addRepository) {
        this.addRepository = addRepository;
    }

    @Override
    public Address addUserAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();
        Address nAddress = new Address();
        BeanUtils.copyProperties(addressDTO, nAddress);
        nAddress.setUserId(currentUserId);
        return addRepository.save(nAddress);
    }

    @Override
    public void deleteUserAddress(Long addressId) {
        Long currentUserId = UserContext.getUserId();
        if (addressId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Address address = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        if (!address.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        addRepository.deleteById(addressId);
    }

    @Override
    public Address updateUserAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();
        Long addressId = addressDTO.getAddressId();
        if (addressId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Address oldAddress = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        if (!oldAddress.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        BeanUtils.copyProperties(addressDTO, oldAddress);
        oldAddress.setUserId(currentUserId);
        return addRepository.save(oldAddress);
    }

    @Override
    public Address queryUserAddress(Long addressId) {
        Long currentUserId = UserContext.getUserId();
        if (addressId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Address address = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
        if (!address.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        return address;
    }
}
