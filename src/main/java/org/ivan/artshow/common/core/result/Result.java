package org.ivan.artshow.common.core.result;

import org.ivan.artshow.common.core.resultcode.ResultCodes;

/**
 * 统一响应结果封装类
 *
 * <p>用于统一封装API接口的返回数据格式，包含状态码、消息和数据三个字段。
 * 支持泛型，可以包装任意类型的数据对象。</p>
 *
 * <p>响应结构：</p>
 * <ul>
 *   <li>code: HTTP状态码或业务错误码</li>
 *   <li>message: 响应消息描述</li>
 *   <li>data: 响应数据（可选，泛型）</li>
 * </ul>
 *
 * <p>使用示例：</p>
 * <pre>
 * // 成功返回数据
 * return Result.success(userList);
 *
 * // 成功无数据
 * return Result.success();
 *
 * // 失败返回
 * return Result.error(ResultCodes.PARAMS_ERROR);
 * </pre>
 *
 * @param <T> 响应数据的类型
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Result<T> {
    /**
     * 状态码（200表示成功，其他表示失败）
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功响应（带数据）
     *
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 成功的Result对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    /**
     * 成功响应（无数据）
     *
     * @param <T> 数据类型
     * @return 成功的Result对象
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    /**
     * 失败响应（自定义错误码和消息）
     *
     * @param code 错误码
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 失败的Result对象
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 失败响应（使用预定义的错误码枚举）
     *
     * @param code 错误码枚举
     * @param <T> 数据类型
     * @return 失败的Result对象
     */
    public static <T> Result<T> error(ResultCodes code) {
        return new Result<>(code.getCode(), code.getMsg(), null);
    }
}