package org.ivan.artshow.module.collection.service;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.collection.pojo.Collection;
import org.ivan.artshow.module.collection.pojo.dto.CollectionDTO;
import org.ivan.artshow.module.collection.repository.CollectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CollectionService - 业务服务实现类
 *
 * <p>CollectionService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class CollectionService implements ICollectionService{
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Collection addCollection(CollectionDTO collection){
        if (collection == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Collection ncollection = new Collection();
        BeanUtils.copyProperties(collection,ncollection);
        return collectionRepository.save(ncollection);
    }

    @Override
    public void deleteCollection(Long collectionId) {
        if (collectionId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        collectionRepository.deleteById(collectionId);
    }

    @Override
    public Collection updateCollection(CollectionDTO Collection){
        if (Collection == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long collectionId = Collection.getCollectionId();
        if (collectionId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Collection ncollection = collectionRepository.findById(collectionId).orElseThrow(()->new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(Collection,ncollection);
        return collectionRepository.save(ncollection);
    }

    @Override
    public Collection queryCollection(Long collectionId) {
        if (collectionId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return collectionRepository.findById(collectionId).orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Collection> queryAllCollectionBatch(List<Long> collectionId){
        if (collectionId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return collectionRepository.findAllById(collectionId);
    }
}
