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

    @Query(value = "SELECT p.id AS id, p.key AS key, p.name AS name, " +
        "p.component AS component, p.path AS path, p.icon AS icon, pr.actions AS actions " +
        "FROM t_permission_role pr " +
        "LEFT JOIN t_permission p " +
        "ON pr.permission_id = p.id " +
        "WHERE pr.role_id = :roleId", nativeQuery = true)
    List<RolePermission> findPermissionByRoleId(@Param("roleId") String roleId);
}
