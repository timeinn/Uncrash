package net.uncrash.authorization.basic.repository;

import net.uncrash.authorization.basic.domain.DefaultRole;
import net.uncrash.authorization.basic.domain.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Sendya
 */
public interface RoleRepository extends JpaRepository<DefaultRole, String> {

    @Query(value = "SELECT new net.uncrash.authorization.basic.domain.RolePermission(p.id, p.name, " +
        "p.component, p.path, p.icon, pr.actions) " +
        "FROM PermissionRole pr " +
        "LEFT JOIN DefaultPermission p " +
        "ON pr.permissionId = p.id " +
        "WHERE pr.roleId = :roleId")
    List<RolePermission> findPermissionByRoleId(@Param("roleId") String roleId);
}
