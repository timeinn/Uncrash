package net.uncrash.authorization.basic.domain;

import lombok.Data;
import net.uncrash.authorization.Permission;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class RolePermission implements Permission {

    private String id;

    private String name;

    private String component;

    private String path;

    private String icon;

    private Boolean showInMenu;

    private Boolean hideChildrenInMenu;

    private Set<String> actions;

    private String originActions;

    public RolePermission() {
    }

    public RolePermission(String id, String name, String component, String path, String icon, String actions) {
        this.id = id;
        this.name = name;
        this.component = component;
        this.path = path;
        this.icon = icon;
        this.showInMenu = false;
        this.hideChildrenInMenu = false;
        this.originActions = actions;
    }

    public Set<String> getActions() {
        return getActionList().stream()
            .map(Action::getAction)
            .collect(Collectors.toSet());
    }

    public Set<Action> getActionList() {
        return Action.json2Actions(this.originActions);
    }
}
