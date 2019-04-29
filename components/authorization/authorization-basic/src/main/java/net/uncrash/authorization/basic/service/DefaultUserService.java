package net.uncrash.authorization.basic.service;

import lombok.RequiredArgsConstructor;
import net.uncrash.authorization.basic.domain.DefaultUser;
import net.uncrash.authorization.basic.repository.UserRepository;
import net.uncrash.server.api.AbstractJpaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sendya
 */
@Service
@RequiredArgsConstructor
public class DefaultUserService extends AbstractJpaService<DefaultUser, String> implements UserService {

    private final UserRepository userRepository;

    @Override
    public JpaRepository<DefaultUser, String> getRepository() {
        return this.userRepository;
    }

}
