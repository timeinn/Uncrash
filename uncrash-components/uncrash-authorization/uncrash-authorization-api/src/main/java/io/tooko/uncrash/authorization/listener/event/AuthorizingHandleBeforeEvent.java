package io.tooko.uncrash.authorization.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * 登陆前事件
 */
public class AuthorizingHandleBeforeEvent extends ApplicationEvent implements AuthorizationEvent {

    private boolean allow = false;

    private boolean execute = true;

    private String message;

    public AuthorizingHandleBeforeEvent(Object source) {
        super(source);
    }

    public boolean isAllow() {
        return allow;
    }

    public boolean isExecute() {
        return execute;
    }

    public String getMessage() {
        return message;
    }

    public void setAllow(boolean allow) {
        execute = false;
        this.allow = allow;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
