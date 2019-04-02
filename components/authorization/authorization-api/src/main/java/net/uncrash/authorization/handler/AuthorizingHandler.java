package net.uncrash.authorization.handler;

import net.uncrash.authorization.define.AuthorizingContext;

/**
 * 权限控制处理器
 */
public interface AuthorizingHandler {

    void handRBAC(AuthorizingContext context);

    default void handler(AuthorizingContext context) {
        handRBAC(context);
    }
}
