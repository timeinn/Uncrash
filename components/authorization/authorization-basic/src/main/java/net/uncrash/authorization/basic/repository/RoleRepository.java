package net.uncrash.authorization.basic.repository;

import net.uncrash.authorization.basic.domain.DefaultRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sendya
 */
public interface RoleRepository extends JpaRepository<DefaultRole, String> {
}
