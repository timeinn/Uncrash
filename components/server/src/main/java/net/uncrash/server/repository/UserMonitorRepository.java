package net.uncrash.server.repository;

import net.uncrash.server.domain.UserMonitor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 监控 dao
 *
 * @author Sendya
 */
public interface UserMonitorRepository extends JpaRepository<UserMonitor, String> {
}
