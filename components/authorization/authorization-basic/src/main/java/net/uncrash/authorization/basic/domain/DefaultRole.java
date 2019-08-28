package net.uncrash.authorization.basic.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.uncrash.authorization.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 默认角色实现
 *
 * @author Sendya
 */
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_role")
public class DefaultRole implements Role {

    @Id
    @ApiModelProperty("主键")
    @GeneratedValue(generator = "GenUUID")
    @GenericGenerator(name = "GenUUID", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "[name]", length = 128)
    private String name;

    @Column(name = "[describe]", length = 256)
    private String describe;

    @Transient
    private List<RolePermission> permissions;

    @Override
    public String getId() {
        return id;
    }

}
