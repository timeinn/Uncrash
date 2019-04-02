package net.uncrash.authorization.listener.event;

import org.springframework.context.ApplicationEvent;

import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractAuthorizationEvent extends ApplicationEvent implements AuthorizationEvent {

    protected String username;

    protected String password;

    private transient Function<String, Object> parameterGetter;

    /**
     * @param username        用户名
     * @param password        密码
     * @param parameterGetter 参数获取函数,用户获取授权时传入的参数
     */
    public AbstractAuthorizationEvent(String username, String password, Function<String, Object> parameterGetter) {
        super(username.concat("/").concat(password));

        if (username == null || password == null || parameterGetter == null) {
            throw new NullPointerException();
        }
        this.username = username;
        this.password = password;
        this.parameterGetter = parameterGetter;
    }

    public <T> Optional<T> getParameter(String name) {
        return Optional.ofNullable((T) parameterGetter.apply(name));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
