package org.ivan.artshow.module.chapter.service;

import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
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
        if (chapter == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Chapter nChapter = new Chapter();
        BeanUtils.copyProperties(chapter,nChapter);
        return chapterRepository.save(nChapter);
    }

    @Override
    public void deleteChapter(Integer chapterId) {
        if (chapterId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        chapterRepository.deleteById(chapterId);
    }

    @Override
    public Chapter updateChapter(ChapterDTO Chapter) {
        if (Chapter == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Integer chapterId = Chapter.getChapterId();
        if (chapterId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Chapter nChapter = chapterRepository.findById(chapterId).orElseThrow(()->new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(Chapter,nChapter);
        return chapterRepository.save(nChapter);
    }

    @Override
    public Chapter queryChapter(Integer chapterId) {
        if (chapterId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return chapterRepository.findById(chapterId)
            .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND));
    }
}
