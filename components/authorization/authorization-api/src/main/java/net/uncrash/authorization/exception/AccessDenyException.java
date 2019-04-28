package net.uncrash.authorization.exception;

import lombok.Getter;

/**
 * 无权限 异常
 *
 * @author Sendya
 */
public class AccessDenyException extends RuntimeException {

    @Getter
    private String code;

    @Getter
    private final int status = 403;

    public AccessDenyException() {
        this("denied access");
    }

    public AccessDenyException(String message) {
        super(message);
        this.code = "403";
    }

    public AccessDenyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDenyException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }
}
