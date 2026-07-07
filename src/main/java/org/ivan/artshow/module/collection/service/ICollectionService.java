package org.ivan.artshow.module.collection.service;

import org.ivan.artshow.module.collection.pojo.Collection;
import org.ivan.artshow.module.collection.pojo.dto.CollectionDTO;

import java.util.List;

/**
 * ICollectionService - 业务服务接口
 *
 * <p>ICollectionService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface ICollectionService {
    public Collection addCollection(CollectionDTO collection);
    public Collection updateCollection(CollectionDTO Collection);
    public Collection queryCollection(Long collectionId);
    public void deleteCollection(Long collectionId);
    public List<Collection> queryAllCollectionBatch(List<Long> collectionId);
}
