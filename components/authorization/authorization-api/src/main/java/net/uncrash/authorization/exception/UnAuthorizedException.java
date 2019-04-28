package net.uncrash.authorization.exception;

/**
 * 未授权 异常
 *
 * @author Sendya
 */
public class UnAuthorizedException extends RuntimeException {

    private final int state;

    public UnAuthorizedException() {
        this("Unauthorized", 401);
    }

    public UnAuthorizedException(String message, int state) {
        super(message);
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
