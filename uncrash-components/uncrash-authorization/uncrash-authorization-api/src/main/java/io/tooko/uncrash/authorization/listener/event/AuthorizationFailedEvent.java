package io.tooko.uncrash.authorization.listener.event;

import java.util.function.Function;

/**
 * 授权事件 后置(失败)
 */
public class AuthorizationFailedEvent extends AbstractAuthorizationEvent {

    private Reason reason;

    private Exception exception;

    public AuthorizationFailedEvent(String username, String password, Function<String, Object> parameterGetter, Reason reason) {
        super(username, password, parameterGetter);
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public enum Reason {
        PASSWORD_ERROR,
        USER_DISABLED,
        USER_NOT_EXISTS,
        OTHER
    }
}
