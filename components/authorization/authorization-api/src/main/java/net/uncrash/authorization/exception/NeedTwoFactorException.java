package net.uncrash.authorization.exception;

import lombok.Getter;

/**
 * 需要两步验证 异常
 *
 * @author Sendya
 */
@Getter
public class NeedTwoFactorException extends RuntimeException {

    private String provider;

    public NeedTwoFactorException(String message, String provider) {
        super(message);
        this.provider = provider;
    }
}
