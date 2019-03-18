package io.tooko.uncrash.authorization.basic.handler;


import io.tooko.uncrash.authorization.define.AuthorizingContext;
import io.tooko.uncrash.authorization.handler.AuthorizingHandler;

public class DefaultAuthorizingHandler implements AuthorizingHandler {

    @Override
    public void handRBAC(AuthorizingContext context) {


        System.out.println("-------------------- handRBAC --------------------");
    }
}
