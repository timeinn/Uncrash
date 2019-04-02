package net.uncrash.agent.repository;

import net.uncrash.agent.domain.ServerAgentLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Server Agent Log Repository for CURD
 *
 * @author Acris
 * @date 2019/04/02
 */
public interface ServerAgentLogRepository extends JpaRepository<ServerAgentLog, String> {

}
