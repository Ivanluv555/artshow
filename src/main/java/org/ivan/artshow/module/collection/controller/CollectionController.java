package org.ivan.artshow.module.collection.controller;

import org.ivan.artshow.common.core.result.Result;
import org.ivan.artshow.module.collection.pojo.Collection;
import org.ivan.artshow.module.collection.pojo.dto.CollectionDTO;
import org.ivan.artshow.module.collection.service.ICollectionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CollectionController - 控制器
 *
 * <p>CollectionController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {

    private final ICollectionService collectionService;

    public CollectionController(ICollectionService collectionService) {
        this.collectionService = collectionService;
    }

    // 1. ADD (Create) -> POST /collection
    @PostMapping
    public Result<Collection> addCollection(@RequestBody @Validated CollectionDTO collection){
        Collection ncollection = collectionService.addCollection(collection);
        return Result.success(ncollection);
    }

    @DeleteMapping
    public void deleteCollection(@RequestParam Long collectionId){
        collectionService.deleteCollection(collectionId);
    }

    @PutMapping
    public Result<Collection> updateCollection(@RequestBody @Validated CollectionDTO collection){
        Collection ncollection = collectionService.updateCollection(collection);
        return Result.success(ncollection);
    }

    @GetMapping
    public Result<Collection> queryCollection(@RequestParam Long collectionId){
        Collection ncollection = collectionService.queryCollection(collectionId);
        return Result.success(ncollection);
    }

    // 2. BATCH QUERY -> POST /collection/batch
    // FIX: Added "/batch" to distinguish this from the addCollection method
    @PostMapping("/batch")
    public Result<List<Collection>> queryAllCollectionBatch(@RequestBody List<Long> collectionId){
        List<Collection> list = collectionService.queryAllCollectionBatch(collectionId);
        return Result.success(list);
    }
}
