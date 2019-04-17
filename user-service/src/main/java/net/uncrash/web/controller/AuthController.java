package net.uncrash.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.User;
import net.uncrash.authorization.annotation.Authorize;
import net.uncrash.logging.api.AccessLogger;
import net.uncrash.web.model.UserRegisterBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
