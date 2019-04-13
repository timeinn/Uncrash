package net.uncrash.authorization;


import net.uncrash.authorization.api.web.Authentication;

import java.util.List;

/**
 * @author Sendya
 */
public class  AuthenticationUser implements Authentication {

    private static final long serialVersionUID = -5917177037722252175L;

    private String token;

    private User user;

    private Role role;

    private List<Permission> permissions;

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
