package net.uncrash.agent.service.impl;

import lombok.RequiredArgsConstructor;
import net.uncrash.agent.domain.AgentLog;
import net.uncrash.agent.repository.AgentLogRepository;
import net.uncrash.agent.service.AgentLogService;
import net.uncrash.core.utils.id.IDGenerator;
import net.uncrash.server.api.AbstractJpaService;
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
public class AgentLogServiceImpl extends AbstractJpaService<AgentLog, String> implements AgentLogService {

    private final AgentLogRepository agentLogRepository;

    @Override
    public JpaRepository<AgentLog, String> getRepository() {
        return agentLogRepository;
    }

    @Override
    public AgentLog save(AgentLog agentLog) {
        agentLog.setId(IDGenerator.UUID.generate());
        agentLog.setCreateTime(LocalDateTime.now());
        return agentLogRepository.save(agentLog);
    }

    @Override
    public Page<AgentLog> findAll(Pageable pageable) {
        return agentLogRepository.findAll(pageable);
    }

}
