package net.uncrash.authorization.basic.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.uncrash.authorization.Permission;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 默认权限实现
 *
 * @author Sendya
 */
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_permission_role")
public class PermissionRole {

    @Id
    @ApiModelProperty("主键")
    @GeneratedValue(generator = "GenUUID")
    @GenericGenerator(name = "GenUUID", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String roleId;

    @Column(length = 32)
    private String permissionId;

    @Column(columnDefinition = "TEXT")
    private String actions;

    public String getId() {
        return id;
    }

    public Set<String> getActions() {
        return getActionList().stream()
            .map(Action::getAction)
            .collect(Collectors.toSet());
    }

    public Set<Action> getActionList() {
        return Action.json2Actions(this.actions);
    }

}
