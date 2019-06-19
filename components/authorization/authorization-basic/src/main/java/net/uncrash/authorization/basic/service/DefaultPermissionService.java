package net.uncrash.authorization.basic.service;

import lombok.RequiredArgsConstructor;
import net.uncrash.authorization.basic.domain.DefaultPermission;
import net.uncrash.authorization.basic.domain.PermissionRole;
import net.uncrash.authorization.basic.repository.PermissionRepository;
import net.uncrash.authorization.basic.repository.PermissionRoleRepository;
import net.uncrash.data.api.AbstractJpaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sendya
 */
@Service
@RequiredArgsConstructor
public class DefaultPermissionService extends AbstractJpaService<DefaultPermission, String> implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionRoleRepository permissionRoleRepository;

    @Override
    public JpaRepository<DefaultPermission, String> getRepository() {
        return this.permissionRepository;
    }

    @Override
    public List<PermissionRole> findAllByRoleId(String roleId) {
        return permissionRoleRepository.findAllByRoleId(roleId);
    }


}
