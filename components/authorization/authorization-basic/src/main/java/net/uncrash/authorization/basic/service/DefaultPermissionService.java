package net.uncrash.authorization.basic.service;

import lombok.RequiredArgsConstructor;
import net.uncrash.authorization.basic.domain.PermissionRaw;
import net.uncrash.authorization.basic.repository.PermissionRepository;
import net.uncrash.data.api.AbstractJpaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sendya
 */
@Service
@RequiredArgsConstructor
public class DefaultPermissionService extends AbstractJpaService<PermissionRaw, String> implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public JpaRepository<PermissionRaw, String> getRepository() {
        return this.permissionRepository;
    }
}
