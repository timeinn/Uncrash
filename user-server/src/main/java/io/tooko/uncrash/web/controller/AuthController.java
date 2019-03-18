package io.tooko.uncrash.web.controller;

import io.tooko.uncrash.authorization.User;
import io.tooko.uncrash.logging.api.AccessLogger;
import io.tooko.uncrash.web.model.UserRegisterBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@AccessLogger("授权服务")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/register")
    @AccessLogger("注册用户")
    public User register(@RequestBody UserRegisterBody body) {
        return null;
    }
}
