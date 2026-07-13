package org.ivan.artshow.common.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.ivan.artshow.common.utils.SnowflakeIdGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Hibernate自定义ID生成器 - 使用雪花算法
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class SnowflakeIdentifierGenerator implements IdentifierGenerator {

    private static final SnowflakeIdGenerator SNOWFLAKE = new SnowflakeIdGenerator();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return SNOWFLAKE.nextId();
    }
}
