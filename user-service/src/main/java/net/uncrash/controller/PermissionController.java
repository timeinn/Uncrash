package net.uncrash.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.Permission;
import net.uncrash.authorization.annotation.Authorize;
import net.uncrash.authorization.basic.domain.DefaultPermission;
import net.uncrash.authorization.basic.service.PermissionService;
import net.uncrash.core.exception.NotFoundException;
import net.uncrash.core.web.model.ResponseMessage;
import net.uncrash.logging.api.AccessLogger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.uncrash.core.web.model.ResponseMessage.ok;

@Slf4j
@Api(tags = "Permission Service")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/permission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AccessLogger("权限管理模块")
@Authorize(permission = "permission")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/")
    @Authorize(action = Permission.ACTION_GET)
    public List<DefaultPermission> list() {
        return permissionService.findAll();
    }

    @GetMapping("/{id}")
    @Authorize(action = Permission.ACTION_GET)
    public ResponseMessage<DefaultPermission> info(@PathVariable("id") String id) {
        return ok(permissionService.findOne(id)
            .orElseThrow(() -> new NotFoundException("permission " + id + " not found")))
            .exclude("actions");
    }

    @PostMapping("/")
    @Authorize(action = Permission.ACTION_ADD)
    public Boolean add(DefaultPermission permission) {
        DefaultPermission p = permissionService.saveAndFlush(permission);
        if (p != null) {
            return true;
        }
        return false;
    }

    @PutMapping("/{id}")
    @Authorize(action = Permission.ACTION_UPDATE)
    public ResponseMessage<Boolean> update(@PathVariable("id") String id, DefaultPermission permission) {
        permission.setIcon(id);
        DefaultPermission p = permissionService.saveAndFlush(permission);
        if (p != null) {
            return ok(true);
        }
        return ok(false);
    }

    @DeleteMapping("/{id}")
    @Authorize(action = Permission.ACTION_DELETE)
    public Boolean delete(@PathVariable("id") String id) {
        permissionService.deleteById(id);
        return true;
    }

}
