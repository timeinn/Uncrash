package net.uncrash.authorization.basic.define;


import net.uncrash.authorization.annotation.Authorize;
import net.uncrash.authorization.api.web.Authentication;
import net.uncrash.authorization.define.AopAuthorizeDefinition;
import net.uncrash.authorization.define.Logical;
import net.uncrash.authorization.define.Phased;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 默认权限权限定义
 */
public class DefaultBasicAuthorizeDefinition implements AopAuthorizeDefinition {

    private Authentication authentication;

    private String[] permissionDescription = {};

    private String[] actionDescription = {};

    private Set<String> permissions = new LinkedHashSet<>();

    private Set<String> actions = new LinkedHashSet<>();

    private Set<String> roles = new LinkedHashSet<>();

    private Set<String> user = new LinkedHashSet<>();


    private String message = "{un_authorized}";

    private Logical logical = Logical.DEFAULT;

    private Phased phased = Phased.BEFORE;

    private Class targetClass;

    private Method targetMethod;

    public Authentication authentication() {
        return authentication;
    }

    @Override
    public Phased getPhased() {
        return phased;
    }

    @Override
    public int getPriority() {
        return Integer.MIN_VALUE;
    }

    @Override
    public boolean isEmpty() {
        return permissions.isEmpty() && roles.isEmpty() && user.isEmpty();
    }

    public void put(Authorize authorize) {
        if (null == authorize || authorize.ignore()) {
            return;
        }
        permissions.addAll(Arrays.asList(authorize.permission()));
        actions.addAll(Arrays.asList(authorize.action()));
        roles.addAll(Arrays.asList(authorize.role()));
        user.addAll(Arrays.asList(authorize.user()));
        if (authorize.logical() != Logical.DEFAULT) {
            logical = authorize.logical();
        }
        message = authorize.message();
        phased = authorize.phased();
    }

    @Override
    public String[] getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String[] permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    @Override
    public String[] getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String[] actionDescription) {
        this.actionDescription = actionDescription;
    }

    @Override
    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public Set<String> getActions() {
        return actions;
    }

    public void setActions(Set<String> actions) {
        this.actions = actions;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public Set<String> getUser() {
        return user;
    }

    public void setUser(Set<String> user) {
        this.user = user;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Logical getLogical() {
        return logical;
    }

    public void setLogical(Logical logical) {
        this.logical = logical;
    }

    public void setPhased(Phased phased) {
        this.phased = phased;
    }

    @Override
    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    public DefaultBasicAuthorizeDefinition(Authentication authentication, String[] permissionDescription, String[] actionDescription, Set<String> permissions, Set<String> actions, Set<String> roles, Set<String> user, String message, Logical logical, Phased phased, Class targetClass, Method targetMethod) {
        this.authentication = authentication;
        this.permissionDescription = permissionDescription;
        this.actionDescription = actionDescription;
        this.permissions = permissions;
        this.actions = actions;
        this.roles = roles;
        this.user = user;
        this.message = message;
        this.logical = logical;
        this.phased = phased;
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
    }

    public DefaultBasicAuthorizeDefinition() {
    }

    @Override
    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "DefaultBasicAuthorizeDefinition{" +
            "authentication=" + authentication +
            ", permissionDescription=" + Arrays.toString(permissionDescription) +
            ", actionDescription=" + Arrays.toString(actionDescription) +
            ", permissions=" + permissions +
            ", actions=" + actions +
            ", roles=" + roles +
            ", user=" + user +
            ", message='" + message + '\'' +
            ", logical=" + logical +
            ", phased=" + phased +
            ", targetClass=" + targetClass +
            ", targetMethod=" + targetMethod +
            '}';
    }
}
