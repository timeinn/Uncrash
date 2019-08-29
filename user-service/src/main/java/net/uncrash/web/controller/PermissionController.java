package net.uncrash.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import net.uncrash.authorization.Permission;
import net.uncrash.authorization.annotation.Authorize;
import net.uncrash.authorization.basic.domain.DefaultPermission;
import net.uncrash.authorization.basic.service.PermissionService;
import net.uncrash.core.exception.NotFoundException;
import net.uncrash.logging.api.AccessLogger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Permission Service")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/permission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AccessLogger("权限管理模块")
@Authorize(permission = "permission")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/{id}")
    @Authorize(action = Permission.ACTION_QUERY)
    public DefaultPermission info(@PathVariable("id") String id) {
        DefaultPermission permission = permissionService.findOne(id)
            .orElseThrow(() -> new NotFoundException("permission " + id + " not found"));
        return permission;
    }

}
