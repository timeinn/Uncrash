package net.uncrash.core.exception;

/**
 * Http Code 400 Bad Request
 * 错误的请求
 *
 * 用于任何参数错误时，抛出的异常
 *
 * @author Sendya
 */
public class BadRequestException extends BusinessException {

    public BadRequestException(String message) {
        super(message, 400);
    }
}
