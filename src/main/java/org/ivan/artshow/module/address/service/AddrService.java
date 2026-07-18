package org.ivan.artshow.module.address.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.address.pojo.Address;
import org.ivan.artshow.module.address.pojo.dto.AddressDTO;
import org.ivan.artshow.module.address.repository.AddRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public Address addUserAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long currentUserId = UserContext.getUserId();
        Address nAddress = new Address();
        BeanUtils.copyProperties(addressDTO, nAddress);
        nAddress.setUserId(currentUserId);

        // 如果是第一个地址或明确设置为默认，则设为默认地址
        long addressCount = addRepository.countByUserId(currentUserId);
        if (addressCount == 0 || Boolean.TRUE.equals(addressDTO.getIsDefault())) {
            // 先清除其他默认地址
            addRepository.clearDefaultByUserId(currentUserId);
            nAddress.setIsDefault(true);
        } else {
            nAddress.setIsDefault(false);
        }

        return addRepository.save(nAddress);
    }

    @Override
    public void deleteUserAddress(Long addressId) {
        Long currentUserId = UserContext.getUserId();
        if (addressId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Address address = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "地址不存在"));
        if (!address.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH, "无权删除此地址");
        }
        addRepository.deleteById(addressId);
    }

    @Override
    @Transactional
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
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "地址不存在"));
        if (!oldAddress.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH, "无权修改此地址");
        }

        // 更新地址信息
        if (addressDTO.getRecipientName() != null) oldAddress.setRecipientName(addressDTO.getRecipientName());
        if (addressDTO.getPhone() != null) oldAddress.setPhone(addressDTO.getPhone());
        if (addressDTO.getRegion() != null) oldAddress.setRegion(addressDTO.getRegion());
        if (addressDTO.getDetailAddress() != null) oldAddress.setDetailAddress(addressDTO.getDetailAddress());

        // 如果设置为默认地址
        if (Boolean.TRUE.equals(addressDTO.getIsDefault())) {
            // 先清除其他默认地址
            addRepository.clearDefaultByUserId(currentUserId);
            oldAddress.setIsDefault(true);
        }

        return addRepository.save(oldAddress);
    }

    @Override
    public Address queryUserAddress(Long addressId) {
        Long currentUserId = UserContext.getUserId();
        if (addressId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Address address = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "地址不存在"));
        if (!address.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH, "无权查询此地址");
        }
        return address;
    }

    @Override
    @Transactional
    public Address setDefaultAddress(Long addressId) {
        if (addressId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        Long currentUserId = UserContext.getUserId();

        // 验证地址存在且属于当前用户
        Address address = addRepository.findById(addressId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "地址不存在"));

        if (!address.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH, "无权操作此地址");
        }

        // 清除当前用户的所有默认地址
        addRepository.clearDefaultByUserId(currentUserId);

        // 设置指定地址为默认
        address.setIsDefault(true);
        return addRepository.save(address);
    }

    @Override
    public List<Address> queryMyAddresses() {
        Long currentUserId = UserContext.getUserId();
        return addRepository.findByUserId(currentUserId);
    }

    @Override
    public Address queryMyDefaultAddress() {
        Long currentUserId = UserContext.getUserId();
        return addRepository.findByUserIdAndIsDefault(currentUserId, true)
                .orElse(null);
    }
}
