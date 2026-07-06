package org.ivan.artshow.common.core.resultcode;

/**
 * Response Status Code Enum
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public enum ResultCodes {
    /**
     * 200 - Operation successful
     */
    SUCCESS(200, "Success"),

    /**
     * 400 - Request error (e.g. login failed)
     */
    ERROR(400, "Login failed"),

    /**
     * 404 - Resource not found
     */
    NOTFOUND(404, "Resource not found"),

    /**
     * 401 - Not logged in or invalid token
     */
    NOTLOGIN(401, "Not logged in"),

    /**
     * 402 - No permission to access
     */
    UNAUTH(402, "No permission"),

    /**
     * 403 - Server refused request
     */
    REFUSE(403, "Server refused connection"),

    /**
     * 413 - Request entity too large
     */
    TOOLARGE(413, "Request entity too large"),

    /**
     * 408 - Request timeout
     */
    OVERTIME(408, "Request timeout"),

    /**
     * 500 - Null pointer exception
     */
    NULLPOINT(500, "Null pointer exception"),

    /**
     * 400 - Invalid parameter
     */
    INVALID_PARAM(400, "Invalid parameter"),

    /**
     * 400 - Insufficient stock
     */
    INSUFFICIENT_STOCK(400, "Insufficient stock");

    /**
     * Status code
     */
    private final int code;

    /**
     * Error message
     */
    private final String msg;

    /**
     * Constructor
     *
     * @param code Status code
     * @param msg Error message
     */
    ResultCodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
