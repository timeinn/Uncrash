package net.uncrash.agent.service;

import net.uncrash.agent.domain.ServerAgentLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Server Agent Log Service Interface
 *
 * @author Acris
 * @date 2019/04/02
 */
public interface ServerAgentLogService {
    Page<ServerAgentLog> findAll(Pageable pageable);
}
