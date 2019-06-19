package net.uncrash.authorization.basic.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import net.uncrash.authorization.Permission;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限角色关联关系
 *
 * @author Sendya
 */
@Data
@Builder
@Table(name = "t_permission")
@Entity
public class DefaultPermission implements Permission {

    @Id
    @ApiModelProperty("主键")
    @GeneratedValue(generator = "GenUUID")
    @GenericGenerator(name = "GenUUID", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "[name]", length = 128)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String actions;

    @Column(name = "[describe]", length = 257)
    private String describe;

    @Override
    public Set<String> getActions() {
        return null;
    }
}
