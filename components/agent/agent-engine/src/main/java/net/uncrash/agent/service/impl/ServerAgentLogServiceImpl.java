package net.uncrash.agent.service.impl;

import lombok.RequiredArgsConstructor;
import net.uncrash.agent.domain.ServerAgentLog;
import net.uncrash.agent.repository.ServerAgentLogRepository;
import net.uncrash.agent.service.AbstractJpaService;
import net.uncrash.agent.service.ServerAgentLogService;
import net.uncrash.core.utils.id.IDGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Server Agent Log Service Implication
 *
 * @author Acris
 * @date 2019/04/02
 */
@Service
@RequiredArgsConstructor
public class ServerAgentLogServiceImpl extends AbstractJpaService<ServerAgentLog, String> implements ServerAgentLogService {

    private final ServerAgentLogRepository serverAgentLogRepository;

    @Override
    public JpaRepository<ServerAgentLog, String> getRepository() {
        return serverAgentLogRepository;
    }

    @Override
    public ServerAgentLog save(ServerAgentLog serverAgentLog) {
        serverAgentLog.setId(IDGenerator.UUID.generate());
        serverAgentLog.setCreateTime(LocalDateTime.now());
        return serverAgentLogRepository.save(serverAgentLog);
    }

    @Override
    public Page<ServerAgentLog> findAll(Pageable pageable) {
        return serverAgentLogRepository.findAll(pageable);
    }

}
