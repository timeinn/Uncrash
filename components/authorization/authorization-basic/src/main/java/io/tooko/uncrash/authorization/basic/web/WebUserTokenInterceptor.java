package io.tooko.uncrash.authorization.basic.web;

import io.tooko.uncrash.authorization.api.web.ParsedToken;
import io.tooko.uncrash.authorization.api.web.TokenParser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WebUserTokenInterceptor extends HandlerInterceptorAdapter {

    private List<TokenParser> tokenParser;

    private boolean basicAuthorization = false;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        List<ParsedToken> tokens = tokenParser.stream()
            .map(parser -> parser.parseToken(request))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        if (tokens.isEmpty()) {

            if (basicAuthorization && handler instanceof HandlerMethod) {
                HandlerMethod method = ((HandlerMethod) handler);

            }
            return true;
        }


        return super.preHandle(request, response, handler);
    }
}
