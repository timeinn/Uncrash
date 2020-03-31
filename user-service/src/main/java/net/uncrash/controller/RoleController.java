package net.uncrash.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.Permission;
import net.uncrash.authorization.annotation.Authorize;
import net.uncrash.authorization.basic.domain.DefaultRole;
import net.uncrash.authorization.basic.service.DefaultRoleService;
import net.uncrash.core.exception.NotFoundException;
import net.uncrash.logging.api.AccessLogger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "Role Service")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AccessLogger("角色管理模块")
@Authorize(permission = "role")
public class RoleController {

    private final DefaultRoleService roleService;

    @GetMapping("/{id}")
    @Authorize(action = Permission.ACTION_GET)
    public DefaultRole info(@PathVariable("id") String id) {
        return roleService.findOne(id)
            .orElseThrow(() -> new NotFoundException("not found"));
    }

    @GetMapping("/")
    @Authorize(action = Permission.ACTION_GET)
    public List<DefaultRole> list() {
        return roleService.findAll();
    }

    @PostMapping("/")
    @Authorize(action = Permission.ACTION_ADD)
    public Boolean add(DefaultRole role) {
        roleService.saveAndFlush(role);
        return true;
    }

    @PutMapping("/{id}")
    @Authorize(action = Permission.ACTION_UPDATE)
    public Boolean update(@PathVariable("id") String id, DefaultRole role) {
        role.setId(id);
        roleService.saveAndFlush(role);
        return true;
    }

    @DeleteMapping("/{id}")
    @Authorize(action = Permission.ACTION_DELETE)
    public Boolean delete(@PathVariable("id") String id) {
        roleService.deleteById(id);
        return true;
    }
}
