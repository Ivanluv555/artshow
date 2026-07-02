package org.ivan.artshow.common.utils;

import java.util.regex.Pattern;

/**
 * 数据验证工具类
 *
 * <p>提供常用的数据格式验证功能，如手机号、邮箱等。
 * 使用正则表达式进行格式校验。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>手机号格式验证：验证是否为11位中国大陆手机号</li>
 *   <li>邮箱格式验证：验证是否为合法的邮箱地址</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class ValidationUtils {
    /**
     * 手机号正则表达式：1开头，第二位为3-9，共11位数字
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    /**
     * 邮箱正则表达式：标准邮箱格式
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    /**
     * 验证手机号格式
     *
     * @param phone 待验证的手机号
     * @return true表示格式正确，false表示格式错误
     */
    public static boolean isPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * 验证邮箱格式
     *
     * @param email 待验证的邮箱地址
     * @return true表示格式正确，false表示格式错误
     */
    public static boolean isEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}