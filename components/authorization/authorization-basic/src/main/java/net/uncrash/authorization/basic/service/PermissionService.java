package net.uncrash.authorization.basic.service;

import net.uncrash.authorization.Permission;
import net.uncrash.authorization.basic.domain.DefaultPermission;
import net.uncrash.authorization.basic.domain.PermissionRole;
import net.uncrash.data.api.JpaService;

import java.util.List;

/**
 * @author Sendya
 */
public interface PermissionService extends JpaService<DefaultPermission, String> {

    List<PermissionRole> findAllByRoleId(String roleId);
}
