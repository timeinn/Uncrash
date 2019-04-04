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
public interface ServerAgentLogService extends JpaService<ServerAgentLog, String> {

    /**
     * Save server agent log to database
     *
     * @param serverAgentLog the log to save
     * @return the log which saved successful
     */
    ServerAgentLog save(ServerAgentLog serverAgentLog);

    /**
     * Query all server agent logs pageable
     *
     * @param pageable Page param
     * @return server agent log list
     */
    Page<ServerAgentLog> findAll(Pageable pageable);
}
