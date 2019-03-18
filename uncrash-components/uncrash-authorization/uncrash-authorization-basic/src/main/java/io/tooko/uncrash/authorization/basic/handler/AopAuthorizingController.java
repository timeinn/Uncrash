package io.tooko.uncrash.authorization.basic.handler;

import io.tooko.core.boost.aop.MethodInterceptorContext;
import io.tooko.core.boost.aop.MethodInterceptorHolder;
import io.tooko.core.utils.AopUtils;
import io.tooko.core.utils.WebUtil;
import io.tooko.uncrash.authorization.annotation.Authorize;
import io.tooko.uncrash.authorization.basic.aop.AopMethodAuthorizeDefinitionParser;
import io.tooko.uncrash.authorization.basic.aop.DefaultAopMethodAuthorizeDefinitionParser;
import io.tooko.uncrash.authorization.define.AuthorizationConst;
import io.tooko.uncrash.authorization.define.AuthorizeDefinition;
import io.tooko.uncrash.authorization.define.AuthorizingContext;
import io.tooko.uncrash.authorization.handler.AuthorizingHandler;
import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AopAuthorizingController extends StaticMethodMatcherPointcutAdvisor implements CommandLineRunner {

    private static final long serialVersionUID = -4291753649948084025L;

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationConst.LOGGER_NAME);

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private DefaultAopMethodAuthorizeDefinitionParser defaultParser = new DefaultAopMethodAuthorizeDefinitionParser();

    private boolean autoParse = true;

    public void setAutoParse(boolean autoParse) {
        this.autoParse = autoParse;
    }

    public AopAuthorizingController(AuthorizingHandler authorizingHandler,
                                    AopMethodAuthorizeDefinitionParser aopMethodAuthorizeDefinitionParser) {

        super((MethodInterceptor) methodInvocation -> {
            MethodInterceptorHolder holder = MethodInterceptorHolder.create(methodInvocation);
            MethodInterceptorContext paramContext = holder.createParamContext();
            AuthorizeDefinition definition = aopMethodAuthorizeDefinitionParser.parse(methodInvocation.getThis().getClass(), methodInvocation.getMethod());

            logger.info("AuthorizeDefinition: {}", definition);

            Object result = null;
            boolean isControl = false;

            if (definition != null && !definition.isEmpty()) {

                AuthorizingContext context = new AuthorizingContext();
                context.setAuthentication(definition.getAuthentication());
                context.setDefinition(definition);
                context.setParamContext(paramContext);

                authorizingHandler.handRBAC(context);

                Map<String, String> headers = WebUtil.getHeaders();

                logger.debug("Headers: {}", headers);

                isControl = true;
            }

            if (!isControl) {
                result = methodInvocation.proceed();
            }

            return result;
        });
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {

        boolean support = AopUtils.findAnnotation(aClass, Controller.class) != null
                || AopUtils.findAnnotation(aClass, RestController.class) != null
                || AopUtils.findAnnotation(aClass, method, Authorize.class) != null;

        if (support) {
            logger.trace("Class: {}\tMethod: {}, {}", aClass, method.getName(), method.getParameters());
            defaultParser.parse(aClass, method);
        }
        return support;
    }

    @Override
    public void run(String... args) throws Exception {
        if (autoParse) {
            List<AuthorizeDefinition> definitions = defaultParser.getAllParsed();

            logger.info("==definitions==:{}", definitions);
            List<AuthorizeDefinition> definitionList = definitions.stream()
                    .filter( def -> !def.isEmpty())
                    .collect(Collectors.toList());

            logger.info("definitionList: {}", definitionList);

            defaultParser.destroy();
        }
    }
}
