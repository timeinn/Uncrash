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
    @Column(length = 32, columnDefinition = "VARCHAR(32) comment '权限唯一键'")
    private String id;

    @Column(name = "[name]", length = 128, columnDefinition = "VARCHAR(128) comment '权限名称'")
    private String name;

    @Column(columnDefinition = "TEXT comment '权限操作级别'")
    private String actions;

    @Column(name = "[describe]", length = 257, columnDefinition = "VARCHAR(128) comment '权限说明'")
    private String describe;

    @Column(name = "[parent]", length = 32, columnDefinition = "VARCHAR(32) comment '父级'")
    private String parent;

    @Column(name = "component", columnDefinition = "VARCHAR(128) comment '前端组件名'")
    private String component;

    @Column(name = "icon", length = 50, columnDefinition = "VARCHAR(128) comment '权限路由图标'")
    private String icon;

    @Column(name = "show_in_menu", columnDefinition = "TINYINT(1) comment '显示到菜单'")
    private Boolean showInMenu;

    @Column(name = "hide_children_in_menu", columnDefinition = "TINYINT(1) comment '隐藏子菜单'")
    private Boolean hideChildrenInMenu;

    @Column(name = "[path]", length = 500, columnDefinition = "VARCHAR(255) comment '路由路径，如没有则自动生成'")
    private String path;

    @Column(name = "[sort]", length = 4, columnDefinition = "int(11) comment '排序字段'")
    private Integer sort;

    @Override
    public Set<String> getActions() {
        return null;
    }
}
