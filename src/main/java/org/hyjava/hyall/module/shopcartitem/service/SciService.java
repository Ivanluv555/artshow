package org.hyjava.hyall.module.shopcartitem.service;

import org.hyjava.hyall.common.auth.UserContext;
import org.hyjava.hyall.common.core.resultcode.ResultCodes;
import org.hyjava.hyall.common.exception.BizException;
import org.hyjava.hyall.module.shopcartitem.pojo.Sci;
import org.hyjava.hyall.module.shopcartitem.pojo.dto.SciDTO;
import org.hyjava.hyall.module.shopcartitem.repository.SciRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SciService implements ISciService {
    @Autowired
    SciRepository sciRepository;

    @Override
    public Sci addSci(SciDTO sciDTO) {
        Integer currentUserId = UserContext.getUserId();

        Sci nSci = new Sci();
        BeanUtils.copyProperties(sciDTO, nSci);
        nSci.setUserId(currentUserId); // 🔒 强制绑定当前用户
        return sciRepository.save(nSci);
    }

    @Override
    public void deleteSci(Integer cartItemId) {
        Integer currentUserId = UserContext.getUserId();

        Sci sci = sciRepository.findById(cartItemId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查
        if (!sci.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }

        sciRepository.deleteById(cartItemId);
    }

    @Override
    public Sci updateSci(SciDTO sciDTO) {
        Integer currentUserId = UserContext.getUserId();
        Integer cartItemId = sciDTO.getCartItemId();

        Sci oldSci = sciRepository.findById(cartItemId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查
        if (!oldSci.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }

        BeanUtils.copyProperties(sciDTO, oldSci);
        oldSci.setUserId(currentUserId); // 保持所有权不变
        return sciRepository.save(oldSci);
    }

    @Override
    public Sci querySci(Integer cartItemId) {
        Integer currentUserId = UserContext.getUserId();
        Sci sci = sciRepository.findById(cartItemId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));

        // 🔒 权限检查
        if (!sci.getUserId().equals(currentUserId)) {
            throw new BizException(ResultCodes.UNAUTH);
        }
        return sci;
    }

    @Override
    public List<Sci> queryAllSciBatch(List<Integer> userIdList) {
        // 这个接口原本是给管理员用的。
        // 如果给普通用户用，必须改写为：return sciRepository.findByUserId(UserContext.getUserId());
        return sciRepository.findAllById(userIdList);
    }
}