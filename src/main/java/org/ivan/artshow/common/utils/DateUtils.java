package org.ivan.artshow.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 *
 * <p>提供日期时间的格式化、解析等常用功能。
 * 使用Java 8的LocalDateTime和DateTimeFormatter进行日期处理。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>获取当前时间的格式化字符串</li>
 *   <li>将LocalDateTime格式化为字符串</li>
 *   <li>将字符串解析为LocalDateTime</li>
 * </ul>
 *
 * <p>标准格式：yyyy-MM-dd HH:mm:ss</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class DateUtils {
    /**
     * 标准日期时间格式
     */
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期时间格式化器
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(STANDARD_FORMAT);

    /**
     * 获取当前时间的格式化字符串
     *
     * @return 当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String now() {
        return LocalDateTime.now().format(FORMATTER);
    }

    /**
     * 格式化日期时间为字符串
     *
     * @param date 待格式化的日期时间对象
     * @return 格式化后的字符串，如果date为null则返回null
     */
    public static String format(LocalDateTime date) {
        return date != null ? date.format(FORMATTER) : null;
    }

    /**
     * 解析日期时间字符串
     *
     * @param dateStr 日期时间字符串，格式：yyyy-MM-dd HH:mm:ss
     * @return LocalDateTime对象
     * @throws java.time.format.DateTimeParseException 如果字符串格式不正确
     */
    public static LocalDateTime parse(String dateStr) {
        return LocalDateTime.parse(dateStr, FORMATTER);
    }
}