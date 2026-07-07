package org.ivan.artshow.module.chapter.controller;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.chapter.pojo.Chapter;
import org.ivan.artshow.module.chapter.pojo.dto.ChapterDTO;
import org.ivan.artshow.module.chapter.service.IChapterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ChapterController - 控制器
 *
 * <p>ChapterController负责处理HTTP请求，提供RESTful API接口。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    private final IChapterService chapterService;

    public ChapterController(IChapterService chapterService) {
        this.chapterService = chapterService;
    }
    @PostMapping
    public Result<Chapter> addChapter(@RequestBody @Validated ChapterDTO chapter){
        Chapter nchapter = chapterService.addChapter(chapter);
        return Result.success(nchapter);
    }

    @DeleteMapping
    public void deleteChapter(@RequestParam Long chapterId){ chapterService.deleteChapter(chapterId);}


    @PutMapping
    public Result<Chapter> updateChapter(@RequestBody @Validated ChapterDTO chapter){
        Chapter nchapter = chapterService.updateChapter(chapter);
        return Result.success(nchapter);
    }

    @GetMapping
    public Result<Chapter> queryChapter(@RequestParam  Long chapterId){
        Chapter nchapter = chapterService.queryChapter(chapterId);
        return Result.success(nchapter);
    }
}
