package net.uncrash.agent.service.impl;

import net.uncrash.agent.domain.ServerAgentLog;
import net.uncrash.agent.repository.ServerAgentLogRepository;
import net.uncrash.agent.service.ServerAgentLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Server Agent Log Service Implication
 *
 * @author Acris
 * @date 2019/04/02
 */
@Service
public class ServerAgentLogServiceImpl implements ServerAgentLogService {
    private final ServerAgentLogRepository serverAgentLogRepository;

    public ServerAgentLogServiceImpl(ServerAgentLogRepository serverAgentLogRepository) {
        this.serverAgentLogRepository = serverAgentLogRepository;
    }

    @Override
    public Page<ServerAgentLog> findAll(Pageable pageable) {
        return serverAgentLogRepository.findAll(pageable);
    }
}
