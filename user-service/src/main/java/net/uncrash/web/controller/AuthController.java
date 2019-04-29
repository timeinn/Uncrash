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
import net.uncrash.logging.api.AccessLogger;
import net.uncrash.web.model.UserRegisterBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user/auth")
@Api(value = "授权服务", tags = "Authorize Service", protocols = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/register")
    @AccessLogger("注册用户")
    public User register(@RequestBody UserRegisterBody body) {
        return null;
    }

    @PostMapping("/login")
    @AccessLogger("登录")
    public Map<String, Object> login(@RequestBody UserRegisterBody body) {
        AuthenticationUser authentication = new AuthenticationUser();
        User user = DefaultUser.builder()
            .id("123")
            .username("admin")
            .name("管理员")
            .build();
        authentication.setUser(user);
        authentication.setRole(DefaultRole.builder()
            .id("roleId")
            .name("testRole")
            .build());
        authentication.setPermissions(Collections.EMPTY_LIST);
        GeneratedToken token = AuthenticationHolder.save(authentication);
        return token.getResponse();
    }
}
