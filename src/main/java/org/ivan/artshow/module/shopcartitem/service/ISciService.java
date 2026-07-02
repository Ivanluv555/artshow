package org.ivan.artshow.module.shopcartitem.service;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.shopcartitem.pojo.Sci;
import org.ivan.artshow.module.shopcartitem.pojo.dto.SciDTO;
import org.springframework.stereotype.Service;

import java.util.List;

//本文件的全名是shopping_cart_item
@Service
/**
 * ISciService - 业务服务接口
 *
 * <p>ISciService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ISciService {
    public Sci addSci(SciDTO sci);
    public void deleteSci(Integer cartItemId);
    public Sci updateSci(SciDTO Sci);
    public Sci querySci(Integer cartItemId);
    public List<Sci> queryAllSciBatch(List<Integer> userIdList);
}
