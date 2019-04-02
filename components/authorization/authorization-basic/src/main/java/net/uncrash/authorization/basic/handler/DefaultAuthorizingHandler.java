package net.uncrash.authorization.basic.handler;


import net.uncrash.authorization.define.AuthorizingContext;
import net.uncrash.authorization.handler.AuthorizingHandler;

public class DefaultAuthorizingHandler implements AuthorizingHandler {

    @Override
    public void handRBAC(AuthorizingContext context) {


        System.out.println("-------------------- handRBAC --------------------");
    }
}
