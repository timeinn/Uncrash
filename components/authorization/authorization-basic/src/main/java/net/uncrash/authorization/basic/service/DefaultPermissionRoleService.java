package net.uncrash.authorization.basic.service;

import lombok.RequiredArgsConstructor;
import net.uncrash.authorization.basic.domain.PermissionRole;
import net.uncrash.authorization.basic.repository.PermissionRoleRepository;
import net.uncrash.data.api.AbstractJpaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sendya
 */
@Service
@RequiredArgsConstructor
public class DefaultPermissionRoleService extends AbstractJpaService<PermissionRole, String> implements PermissionRoleService {

    private final PermissionRoleRepository permissionRoleRepository;

    @Override
    public JpaRepository<PermissionRole, String> getRepository() {
        return permissionRoleRepository;
    }
}
