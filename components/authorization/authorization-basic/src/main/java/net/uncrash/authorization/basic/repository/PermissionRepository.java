package net.uncrash.authorization.basic.repository;

import net.uncrash.authorization.basic.domain.PermissionRaw;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sendya
 */
public interface PermissionRepository extends JpaRepository<PermissionRaw, String> {
}
