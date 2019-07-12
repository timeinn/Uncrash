package net.uncrash.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.AuthenticationHolder;
import net.uncrash.authorization.AuthenticationUser;
import net.uncrash.authorization.User;
import net.uncrash.authorization.annotation.Authorize;
import net.uncrash.authorization.api.web.Authentication;
import net.uncrash.authorization.api.web.GeneratedToken;
import net.uncrash.authorization.basic.domain.DefaultRole;
import net.uncrash.authorization.basic.domain.DefaultUser;
import net.uncrash.authorization.basic.service.UserService;
import net.uncrash.authorization.listener.event.*;
import net.uncrash.core.exception.NotFoundException;
import net.uncrash.core.utils.WebUtil;
import net.uncrash.logging.api.AccessLogger;
import net.uncrash.web.model.UserRegisterBody;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@RestController
@RequestMapping("/user/auth")
@Api(value = "授权服务", tags = "Authorize Service", protocols = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/register")
    @AccessLogger("注册用户")
    public User register(@RequestBody UserRegisterBody body) {
        User user = userService.register(body.getEmail(), body.getUsername(), body.getPassword());
        RegisterSuccessEvent event = new RegisterSuccessEvent(user);
        eventPublisher.publishEvent(event);
        return user;
    }

    @PostMapping("/login")
    @AccessLogger("登录")
    public ResponseEntity login(@RequestBody UserRegisterBody body) {
        return doLogin(body.getUsername(), body.getPassword(), new HashMap<>(8));
    }

    @PostMapping("/logout")
    @AccessLogger("登出")
    public ResponseEntity logout(Authentication authentication) {
        this.doLogout(authentication);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity doLogin(String username, String password, Map<String, ?> parameter) {
        AuthorizationFailedEvent.Reason reason = AuthorizationFailedEvent.Reason.OTHER;
        Function<String, Object> parameterGetter = parameter::get;

        try {
            // 触发事件 - 用户密码解码 :注意不能异步:
            AuthorizationDecodeEvent decodeEvent = new AuthorizationDecodeEvent(username, password, parameterGetter);
            eventPublisher.publishEvent(decodeEvent);
            username = decodeEvent.getUsername();
            password = decodeEvent.getPassword();

            // 触发事件 - 尝试开始登陆
            AuthorizationBeforeEvent beforeEvent = new AuthorizationBeforeEvent(username, password, parameterGetter);
            eventPublisher.publishEvent(beforeEvent);

            GeneratedToken generatedToken = userService.selectByUserNameAndPassword(username, password);


            Authentication authentication = AuthenticationHolder.get(generatedToken.getToken());
            DefaultUser user = (DefaultUser) authentication.getUser();
            // 登陆成功事件
            AuthorizationSuccessEvent successEvent = new AuthorizationSuccessEvent(authentication, parameterGetter);
            String loginIp = WebUtil.getIpAddr();
            successEvent.getResult().put("loginIp", loginIp);
            successEvent.getResult().putAll(generatedToken.getResponse());
            eventPublisher.publishEvent(successEvent);

            return ResponseEntity.ok(generatedToken.getResponse());
        } catch (Exception e) {
            // 异常，触发事件
            AuthorizationFailedEvent failedEvent = new AuthorizationFailedEvent(username, password, parameterGetter, reason);
            failedEvent.setException(e);
            eventPublisher.publishEvent(failedEvent);
            // return ResponseEntity.badRequest().build();
            throw e;
        }
    }

    private void doLogout(Authentication authentication) {
        AuthenticationHolder.remove(authentication);
        // 推送退出事件
        AuthorizationExitEvent exitEvent = new AuthorizationExitEvent(authentication);
        eventPublisher.publishEvent(exitEvent);
    }
}
