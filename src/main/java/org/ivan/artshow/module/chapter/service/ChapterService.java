package org.ivan.artshow.module.chapter.service;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.chapter.pojo.Chapter;
import org.ivan.artshow.module.chapter.pojo.dto.ChapterDTO;
import org.springframework.beans.BeanUtils;
import org.ivan.artshow.module.chapter.repository.ChapterRepository;
import org.springframework.stereotype.Service;

/**
 * ChapterService - 业务服务实现类
 *
 * <p>ChapterService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class ChapterService implements IChapterService {
    private final ChapterRepository chapterRepository;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }
    @Override
    public Chapter addChapter(ChapterDTO chapter) {
        Chapter nChapter = new Chapter();
        BeanUtils.copyProperties(chapter,nChapter);
        return chapterRepository.save(nChapter);
    }

    @Override
    public void deleteChapter(Integer chapterId) { chapterRepository.deleteById(chapterId);}

    @Override
    public Chapter updateChapter(ChapterDTO Chapter) {
        Integer chapterId = Chapter.getChapterId();
        Chapter nChapter = chapterRepository.findById(chapterId).orElseThrow(()->new RuntimeException("要更新的章节不存在,ID:"+chapterId));
        BeanUtils.copyProperties(Chapter,nChapter);
        return chapterRepository.save(nChapter);
    }

    @Override
    public Chapter queryChapter(Integer chapterId) { return chapterRepository.findById(chapterId)
            .orElseThrow(() -> new RuntimeException("章节不存在,ID:" + chapterId)); }
}
