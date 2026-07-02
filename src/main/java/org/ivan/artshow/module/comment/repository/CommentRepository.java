package org.ivan.artshow.module.comment.repository;

import org.ivan.artshow.module.comment.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CommentRepository - 数据访问接口
 *
 * <p>CommentRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
