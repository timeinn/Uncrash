package net.uncrash.core.exception;

/**
 * Http Code 404 Not Found
 *
 * 用于找不到数据或对象时异常
 *
 * @author Sendya
 */
public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(message, 404);
    }
}
