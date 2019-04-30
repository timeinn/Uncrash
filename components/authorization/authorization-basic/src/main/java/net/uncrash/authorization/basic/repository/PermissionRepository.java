package net.uncrash.authorization.basic.repository;

import net.uncrash.authorization.basic.domain.PermissionRaw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Sendya
 */
public interface PermissionRepository extends JpaRepository<PermissionRaw, String> {

    /**
     * 根据角色 ID 查找所有权限
     * @param roleId
     * @return
     */
    List<PermissionRaw> findAllByRoleId(String roleId);
}
