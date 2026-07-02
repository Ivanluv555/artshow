package org.ivan.artshow.common.exception;

import lombok.Getter;
import org.ivan.artshow.common.core.resultcode.ResultCodes;

/**
 * 业务异常类
 *
 * <p>自定义业务异常，继承自RuntimeException。用于在业务逻辑中抛出特定的业务错误，
 * 携带错误码和错误消息，便于统一异常处理和前端展示。</p>
 *
 * <p>主要特性：</p>
 * <ul>
 *   <li>封装业务错误码和错误消息</li>
 *   <li>继承RuntimeException，无需强制捕获</li>
 *   <li>与{@link ResultCodes}枚举配合使用，统一管理错误码</li>
 *   <li>被{@link org.ivan.artshow.common.exception.GlobalExceptionHandler}统一处理</li>
 * </ul>
 *
 * <p>使用示例：</p>
 * <pre>
 * if (user == null) {
 *     throw new BizException(ResultCodes.USER_NOT_FOUND);
 * }
 * </pre>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    @Getter
    public Integer code;

    /**
     * 构造业务异常
     *
     * @param code 错误码枚举，包含错误码和错误消息
     */
    public BizException (ResultCodes code) {
        super(code.getMsg());
        this.code = code.getCode();
    }
}
