package net.uncrash.authorization.basic.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.uncrash.authorization.Role;
import net.uncrash.authorization.User;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
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
@Table(name = "s_user")
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
    private String password;

    @Column(length = 16)
    private String salt;

    @Column(length = 128)
    private String email;

    @Column(name = "[name]", length = 128)
    private String name;

    @Column(length = 6)
    private String type;

    @Column(columnDefinition = "TINYINT(1)", length = 1)
    private Byte status;

    @CreatedDate
    @Column
    private Date createdTime;

    @CreatedBy
    @Column
    private String createdBy;

    @LastModifiedDate
    @Column
    private Date updatedTime;

    @LastModifiedBy
    @Column
    private String updatedBy;

    /**
     * 单一角色关联用
     */
    @Column(name = "role_id")
    private String roleId;

    /**
     * 多角色关联
     */
    @Transient
    private List<DefaultRole> roles;

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
        return roleId;
    }

    @Override
    public Byte getStatus() {
        return status;
    }

    public List<DefaultRole> getRoles() {
        return roles;
    }
}
