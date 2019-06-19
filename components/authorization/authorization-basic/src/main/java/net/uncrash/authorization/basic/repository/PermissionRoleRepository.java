package net.uncrash.authorization.basic.repository;

import net.uncrash.authorization.basic.domain.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRoleRepository extends JpaRepository<PermissionRole, String> {

    List<PermissionRole> findAllByRoleId(String roleId);

}
