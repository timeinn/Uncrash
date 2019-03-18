package io.tooko.uncrash.authorization.define;

import java.lang.reflect.Method;

public interface AopAuthorizeDefinition extends AuthorizeDefinition {

    Class getTargetClass();

    Method getTargetMethod();

}
