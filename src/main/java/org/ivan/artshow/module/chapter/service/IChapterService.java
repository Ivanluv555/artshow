package org.ivan.artshow.module.chapter.service;

import org.ivan.artshow.module.chapter.pojo.Chapter;
import org.ivan.artshow.module.chapter.pojo.dto.ChapterDTO;
import org.springframework.stereotype.Service;

@Service
/**
 * IChapterService - 业务服务接口
 *
 * <p>IChapterService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IChapterService {
    public Chapter queryChapter(Long chapterId);
    public void deleteChapter(Long chapterId);
    public Chapter updateChapter(ChapterDTO Chapter);
    public Chapter addChapter(ChapterDTO chapter);
}
