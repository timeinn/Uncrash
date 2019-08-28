package net.uncrash.authorization.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {

    private String id;

    private String key;

    private String name;

    private String component;

    private String path;

    private String icon;

    private Boolean showInMenu;

    private Boolean hideChildrenInMenu;

    private String actions;

}
