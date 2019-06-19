package net.uncrash.authorization.basic.handler;

import net.uncrash.authorization.AuthenticationHolder;
import net.uncrash.authorization.AuthenticationUser;
import net.uncrash.authorization.api.web.Authentication;
import net.uncrash.authorization.basic.domain.DefaultUser;
import net.uncrash.core.utils.id.IDGenerator;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自动注入登录信息到 Controller
 * @author Sendya
 */
public class AuthenticationHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(AuthenticationUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        AuthenticationUser authenticationUser = (AuthenticationUser) AuthenticationHolder.get();
        // 设置一个默认值
        if (authenticationUser == null) {
            authenticationUser = new AuthenticationUser();
            authenticationUser.setUser(DefaultUser.builder()
                .id(IDGenerator.UUID_NO_SEPARATOR.generate())
                .build());
        }
        return authenticationUser;
    }
}
