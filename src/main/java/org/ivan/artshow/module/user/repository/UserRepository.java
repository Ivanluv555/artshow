package org.ivan.artshow.module.user.repository;
import org.ivan.artshow.common.core.result.Result;

import org.ivan.artshow.module.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * UserRepository - 数据访问接口
 *
 * <p>UserRepository继承JpaRepository，提供数据库操作方法。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
