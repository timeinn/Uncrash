package net.uncrash.server.service;

import lombok.RequiredArgsConstructor;
import net.uncrash.data.api.AbstractJpaService;
import net.uncrash.server.domain.UserMonitor;
import net.uncrash.server.repository.UserMonitorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sendya
 */
@Service
@RequiredArgsConstructor
public class UserMonitorServiceImpl extends AbstractJpaService<UserMonitor, String> implements UserMonitorService {

    private final UserMonitorRepository repository;

    @Override
    public JpaRepository<UserMonitor, String> getRepository() {
        return repository;
    }
}
