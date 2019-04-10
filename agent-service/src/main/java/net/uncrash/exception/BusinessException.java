package net.uncrash.exception;

/**
 * 业务异常
 *
 * @author Sendya
 */
public class BusinessException extends RuntimeException {

    private int status = 400;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BusinessException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
