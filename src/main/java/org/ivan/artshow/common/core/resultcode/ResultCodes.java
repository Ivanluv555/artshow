package org.ivan.artshow.common.core.resultcode;

import lombok.Getter;

/**
 * 响应状态码枚举
 *
 * <p>定义系统中使用的所有业务状态码和对应的错误消息。
 * 与HTTP状态码保持一致，便于前端统一处理。</p>
 *
 * <p>状态码分类：</p>
 * <ul>
 *   <li>2xx：成功状态</li>
 *   <li>4xx：客户端错误（未登录、无权限、请求错误等）</li>
 *   <li>5xx：服务器错误</li>
 * </ul>
 *
 * <p>使用示例：</p>
 * <pre>
 * throw new BizException(ResultCodes.NOTLOGIN);
 * return Result.error(ResultCodes.NOTFOUND);
 * </pre>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public enum ResultCodes {
    /**
     * 200 - 操作成功
     */
    SUCCESS(200, "成功"),

    /**
     * 400 - 请求错误（如登录失败）
     */
    ERROR(400, "登录错误"),

    /**
     * 404 - 资源未找到
     */
    NOTFOUND(404, "没有找到资源"),

    /**
     * 401 - 未登录或令牌无效
     */
    NOTLOGIN(401, "未登录"),

    /**
     * 402 - 无权限访问
     */
    UNAUTH(402, "没有权限"),

    /**
     * 403 - 服务器拒绝请求
     */
    REFUSE(403, "服务器拒绝连接"),

    /**
     * 413 - 请求实体过大
     */
    TOOLARGE(413, "请求实体过大"),

    /**
     * 408 - 请求超时
     */
    OVERTIME(408, "请求超时");

    /**
     * 状态码
     */
    @Getter
    public final int code;

    /**
     * 错误消息
     */
    @Getter
    public final String msg;

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param msg 错误消息
     */
    ResultCodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
