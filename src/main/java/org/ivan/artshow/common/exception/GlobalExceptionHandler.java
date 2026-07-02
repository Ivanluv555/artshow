package org.ivan.artshow.common.exception;

import org.ivan.artshow.common.core.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * <p>使用@RestControllerAdvice注解，统一处理系统中抛出的各种异常，
 * 将异常信息转换为标准的Result响应格式返回给前端。</p>
 *
 * <p>处理的异常类型：</p>
 * <ul>
 *   <li>BizException：业务异常，返回业务定义的错误码和消息</li>
 *   <li>Exception：通用异常，返回500状态码和异常消息</li>
 * </ul>
 *
 * <p>优点：</p>
 * <ul>
 *   <li>统一异常处理逻辑，避免在每个Controller中重复编写异常处理代码</li>
 *   <li>统一响应格式，便于前端统一处理</li>
 *   <li>集中记录异常日志，便于问题排查</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理通用异常
     *
     * <p>捕获所有未被特定处理器捕获的异常，返回500状态码和异常消息。
     * 这是异常处理的兜底方案。</p>
     *
     * @param e 异常对象
     * @return 包含错误信息的Result对象
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        e.printStackTrace(); // 上线之前改成 log.error("...", e);
        return Result.error(500, e.getMessage() != null ? e.getMessage() : "服务器通用错误");
    }

    /**
     * 处理业务异常
     *
     * <p>捕获业务逻辑中主动抛出的BizException，
     * 返回业务定义的错误码和错误消息。</p>
     *
     * @param e 业务异常对象
     * @return 包含业务错误信息的Result对象
     */
    @ExceptionHandler(BizException.class)
    public Result<String> handleBizException(BizException e) {
        // 直接返回业务异常中定义的 Code (如 404, 401) 和 Message
        return Result.error(e.getCode(), e.getMessage());
    }
}