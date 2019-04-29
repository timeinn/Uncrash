package net.uncrash.authorization.basic.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.uncrash.authorization.Role;
import net.uncrash.authorization.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 默认用户实体类实现
 *
 * @author Sendya
 */
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class DefaultUser implements User {

    @Id
    @ApiModelProperty("主键")
    @GeneratedValue(generator = "GenUUID")
    @GenericGenerator(name = "GenUUID", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(unique = true, length = 128)
    private String username;

    @Column(length = 128)
    private String email;

    @Column(name = "[name]", length = 128)
    private String name;

    @Column(length = 6)
    private String type;

    /**
     * 单一角色关联用
     */
    @Transient
    private String role;

    /**
     * 多角色关联
     */
    @Transient
    private List<Role> roles;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getRole() {
        return role;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
