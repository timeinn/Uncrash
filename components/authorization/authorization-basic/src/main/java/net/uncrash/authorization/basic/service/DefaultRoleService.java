package net.uncrash.authorization.basic.service;

import lombok.RequiredArgsConstructor;
import net.uncrash.authorization.basic.domain.DefaultRole;
import net.uncrash.authorization.basic.repository.RoleRepository;
import net.uncrash.data.api.AbstractJpaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sendya
 */
@Service
@RequiredArgsConstructor
public class DefaultRoleService extends AbstractJpaService<DefaultRole, String> implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public JpaRepository<DefaultRole, String> getRepository() {
        return this.roleRepository;
    }
}
