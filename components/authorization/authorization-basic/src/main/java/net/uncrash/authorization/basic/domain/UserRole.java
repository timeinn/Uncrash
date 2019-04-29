package net.uncrash.authorization.basic.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 用户角色关联
 *
 * @author Sendya
 */
@Data
@Builder
public class UserRole {

    private String userId;

    private String roleId;

}
