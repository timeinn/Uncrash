package io.tooko.uncrash.authorization.listener.event;

import io.tooko.uncrash.authorization.api.web.Authentication;
import org.springframework.context.ApplicationEvent;

/**
 * 账户退出事件
 */
public class AuthorizationExitEvent extends ApplicationEvent implements AuthorizationEvent {

    private Authentication authentication;

    public AuthorizationExitEvent(Authentication authentication) {
        super(authentication);
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
