package org.ivan.artshow.common.config;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * 雪花算法ID生成器注解
 *
 * 使用方式：
 * <pre>
 * {@code
 * @Entity
 * public class MyEntity {
 *     @Id
 *     @SnowflakeId
 *     private Long id;
 * }
 * }
 * </pre>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@IdGeneratorType(SnowflakeIdentifierGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface SnowflakeId {
}
