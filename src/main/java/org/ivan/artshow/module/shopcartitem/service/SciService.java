package org.ivan.artshow.module.shopcartitem.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.ivan.artshow.module.shopcartitem.pojo.dto.SciDTO;
import org.ivan.artshow.module.shopcartitem.repository.SciRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SciService - 业务服务实现类
 *
 * <p>SciService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class SciService implements ISciService {
    private final SciRepository sciRepository;

    public SciService(SciRepository sciRepository) {
        this.sciRepository = sciRepository;
    }

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
