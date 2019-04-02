package io.tooko.uncrash.authorization.define;

import io.tooko.core.boost.aop.MethodInterceptorContext;
import io.tooko.uncrash.authorization.api.web.Authentication;

/**
 * 权限控制上下文
 */
public class AuthorizingContext {

    private AuthorizeDefinition definition;

    private Authentication authentication;

    private MethodInterceptorContext paramContext;

    public AuthorizeDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(AuthorizeDefinition definition) {
        this.definition = definition;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public MethodInterceptorContext getParamContext() {
        return paramContext;
    }

    public void setParamContext(MethodInterceptorContext paramContext) {
        this.paramContext = paramContext;
    }
}
