package net.uncrash.authorization.basic.handler;


import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.Permission;
import net.uncrash.authorization.Role;
import net.uncrash.authorization.api.web.Authentication;
import net.uncrash.authorization.define.AuthorizeDefinition;
import net.uncrash.authorization.define.AuthorizingContext;
import net.uncrash.authorization.define.Logical;
import net.uncrash.authorization.exception.AccessDenyException;
import net.uncrash.authorization.exception.UnAuthorizedException;
import net.uncrash.authorization.handler.AuthorizingHandler;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class DefaultAuthorizingHandler implements AuthorizingHandler {

    @Override
    public void handRBAC(AuthorizingContext context) {
        log.info("-------------------- handRBAC --------------------");

        boolean access = true;

        AuthorizeDefinition definition = context.getDefinition();
        Authentication authentication = context.getAuthentication();

        if (authentication == null) {
            throw new UnAuthorizedException();
        }

        // 处理条件逻辑
        Logical logical = definition.getLogical() == Logical.DEFAULT ? Logical.OR : definition.getLogical();
        boolean logicalIsOr = logical == Logical.OR;

        Set<String> roles = definition.getRoles();
        Set<String> permissions = definition.getPermissions();
        Set<String> actions = definition.getActions();

        if (!definition.getPermissions().isEmpty()) {
            if (log.isInfoEnabled()) {
                log.info("执行权限控制: 权限{}({}), 操作{}",
                    definition.getPermissionDescription(),
                    permissions,
                    actions);
            }

            if (authentication.getPermissions() != null) {
                List<Permission> permissionList = authentication.getPermissions().stream()
                    .filter(permission -> {
                        // 未持有任何一个权限
                        if (permissions.contains(permission.getId())) {
                            return false;
                        }

                        // 未配置 action
                        if (actions.isEmpty()) {
                            return true;
                        }

                        // 已配置 action, 进行校验
                        List<String> actionList = permission.getActions()
                            .stream()
                            .filter(actions::contains)
                            .collect(Collectors.toList());

                        if (actionList.isEmpty()) {
                            return false;
                        }

                        // 如果设定的条件是 OR 则只要满足一个 action 条件即可
                        return logicalIsOr || permission.getActions().containsAll(actions);
                   }).collect(Collectors.toList());

                access = logicalIsOr ?
                    !CollectionUtils.isEmpty(permissions) :
                    //权限数量和配置的数量相同
                    permissionList.size() == permissions.size();
            }
        }

        if (!roles.isEmpty()) {
            if (log.isInfoEnabled()) {
                log.info("检查 角色权限 :{}, definition: {}", roles, definition.getRoles());
            }

            if (authentication.getRole() != null) {
                Function<Predicate<String>, Boolean> func = logicalIsOr
                    ? roles.stream()::anyMatch
                    : roles.stream()::allMatch;

                access = func.apply(role -> authentication.getRole().equals(role));
            }
        }

        if (access) {
            throw new AccessDenyException(definition.getMessage());
        }
    }
}
