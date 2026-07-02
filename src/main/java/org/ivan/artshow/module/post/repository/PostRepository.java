package org.ivan.artshow.module.post.repository;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.post.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * PostRepository - 数据访问接口
 *
 * <p>PostRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface PostRepository extends JpaRepository<Post,Integer> {
}
