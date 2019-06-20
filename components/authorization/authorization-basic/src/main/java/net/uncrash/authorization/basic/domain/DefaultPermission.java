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

/**
 * 权限角色关联关系
 *
 * @author Sendya
 */
@Data
@Builder
@Table(name = "t_permission")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DefaultPermission implements Permission {

    @Id
    @ApiModelProperty("主键")
    @GeneratedValue(generator = "GenUUID")
    @GenericGenerator(name = "GenUUID", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "[name]", length = 128, columnDefinition = "comment '权限名称'")
    private String name;

    @Column(columnDefinition = "TEXT comment '权限操作级别'")
    private String actions;

    @Column(name = "[describe]", length = 257, columnDefinition = "comment '权限说明'")
    private String describe;

    @Column(name = "[sort]", length = 4, columnDefinition = "comment '排序字段'")
    private Integer sort;

    @Override
    public Set<String> getActions() {
        return null;
    }
}
