package net.uncrash.exception;

public class BadRequestException extends RuntimeException {
    private int status = 400;

    public BadRequestException(String message) {
        this(message, 400);
    }

    public BadRequestException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BadRequestException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
