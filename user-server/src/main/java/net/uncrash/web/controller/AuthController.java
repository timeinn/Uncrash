package net.uncrash.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.User;
import net.uncrash.logging.api.AccessLogger;
import net.uncrash.web.model.UserRegisterBody;
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
