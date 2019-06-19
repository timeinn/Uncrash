package net.uncrash.authorization.basic.repository;

import net.uncrash.authorization.basic.domain.DefaultPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Sendya
 */
public interface PermissionRepository extends JpaRepository<DefaultPermission, String> {

}
