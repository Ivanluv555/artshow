package org.ivan.artshow.common.exception;

import org.ivan.artshow.common.core.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler
 *
 * <p>Uses @RestControllerAdvice annotation to uniformly handle various exceptions thrown in the system,
 * converting exception information into standard Result response format for frontend.</p>
 *
 * <p>Handled Exception Types:</p>
 * <ul>
 *   <li>BizException: Business exceptions, returns business-defined error code and message</li>
 *   <li>Exception: Generic exceptions, returns 500 status code and exception message</li>
 * </ul>
 *
 * <p>Advantages:</p>
 * <ul>
 *   <li>Unified exception handling logic, avoiding repetitive code in each Controller</li>
 *   <li>Unified response format, convenient for frontend processing</li>
 *   <li>Centralized exception logging for easier troubleshooting</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle generic exceptions
     *
     * <p>Catches all exceptions not caught by specific handlers, returns 500 status code and exception message.
     * This is the fallback exception handling mechanism.</p>
     *
     * @param e Exception object
     * @return Result object containing error information
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("Unhandled exception occurred", e);
        return Result.error(500, e.getMessage() != null ? e.getMessage() : "Internal server error");
    }

    /**
     * Handle business exceptions
     *
     * <p>Catches BizException actively thrown in business logic,
     * returns business-defined error code and message.</p>
     *
     * @param e Business exception object
     * @return Result object containing business error information
     */
    @ExceptionHandler(BizException.class)
    public Result<String> handleBizException(BizException e) {
        log.warn("Business exception: [{}] {}", e.getCode(), e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }
}