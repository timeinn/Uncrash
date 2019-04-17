package net.uncrash.authorization.basic.entity;


import lombok.Builder;
import lombok.Data;
import net.uncrash.authorization.User;

/**
 * 默认用户实体类实现
 */
@Builder
@Data
public class DefaultUser implements User {

    private String id;

    private String username;

    private String name;

    private String type;

    private String role;

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
}
