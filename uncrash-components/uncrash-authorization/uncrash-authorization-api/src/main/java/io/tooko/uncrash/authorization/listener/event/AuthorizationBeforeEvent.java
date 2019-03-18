package io.tooko.uncrash.authorization.listener.event;

import java.util.function.Function;

/**
 * 授权事件 前置
 */
public class AuthorizationBeforeEvent extends AbstractAuthorizationEvent {

    public AuthorizationBeforeEvent(String username, String password, Function<String, Object> parameterGetter) {
        super(username, password, parameterGetter);
    }
}
