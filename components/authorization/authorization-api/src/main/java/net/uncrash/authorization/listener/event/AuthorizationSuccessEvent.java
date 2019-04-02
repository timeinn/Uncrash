package net.uncrash.authorization.listener.event;

import net.uncrash.authorization.api.web.Authentication;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * 授权事件 后置(成功)
 */
public class AuthorizationSuccessEvent extends ApplicationEvent implements AuthorizationEvent {

    private Authentication authentication;

    private transient Function<String, Object> parameterGetter;

    private Map<String, Object> result = new HashMap<>();

    public AuthorizationSuccessEvent(Authentication authentication, Function<String, Object> parameterGetter) {
        super(authentication);
        this.authentication = authentication;
        this.parameterGetter = parameterGetter;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public <T> Optional<T> getParameter(String name) {
        return Optional.ofNullable((T) parameterGetter.apply(name));
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
