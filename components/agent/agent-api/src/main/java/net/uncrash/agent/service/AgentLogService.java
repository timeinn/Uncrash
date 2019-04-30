package net.uncrash.agent.service;

import net.uncrash.agent.domain.AgentLog;
import net.uncrash.data.api.JpaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Agent log service interface
 *
 * @author Acris
 * @date 2019/04/02
 */
public interface AgentLogService extends JpaService<AgentLog, String> {

    /**
     * Save server agent log to database
     *
     * @param agentLog the log to save
     * @return the log which saved successful
     */
    AgentLog save(AgentLog agentLog);

    /**
     * Query all server agent logs pageable
     *
     * @param pageable page param
     * @return server agent log list
     */
    Page<AgentLog> findAll(Pageable pageable);
}
