package net.uncrash.agent.repository;

import net.uncrash.agent.domain.AgentLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Agent log repository for CURD
 *
 * @author Acris
 * @date 2019/04/02
 */
public interface AgentLogRepository extends JpaRepository<AgentLog, String> {

}
