package net.uncrash.web.controller;

import net.uncrash.authorization.Permission;
import net.uncrash.authorization.annotation.Authorize;
import net.uncrash.web.bean.HelloConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
@Authorize(permission = "user")
public class HelloController {

    private final HelloConfig helloConfig;

    @GetMapping("/")
    @Authorize(ignore = true)
    public String hello() {
        return helloConfig.getName();
    }

    @GetMapping("/query")
    @Authorize(action = Permission.ACTION_QUERY)
    public String permissionQueryHello() {
        return "query ok";
    }

    @GetMapping("/add")
    @Authorize(action = Permission.ACTION_ADD)
    public String permissionAddHello() {
        return "add ok";
    }

    @GetMapping("/edit")
    @Authorize(action = Permission.ACTION_UPDATE)
    public String permissionEditHello() {
        return "edit ok";
    }

    @GetMapping("/delete")
    @Authorize(action = Permission.ACTION_DELETE)
    public String permissionDeleteHello() {
        return "delete ok";
    }
}
