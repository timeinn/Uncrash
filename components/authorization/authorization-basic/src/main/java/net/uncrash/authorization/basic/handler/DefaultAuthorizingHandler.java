package net.uncrash.authorization.basic.handler;


import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.Permission;
import net.uncrash.authorization.api.web.Authentication;
import net.uncrash.authorization.basic.domain.DefaultRole;
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

        Set<String> rolesDef = definition.getRoles();
        Set<String> permissionsDef = definition.getPermissions();
        Set<String> actionsDef = definition.getActions();
        Set<String> usersDef = definition.getUser();
        // 单角色检查
        Set<String> roleKeys = Set.of(authentication.getRole().getName());

        // 多角色检查
        //Set<String> roleKeys = authentication.getRoles().stream().map(item -> item.getName()).collect(Collectors.toSet());

        // 检查权限
        if (!definition.getPermissions().isEmpty()) {
            if (log.isInfoEnabled()) {
                log.info("执行权限控制: 权限{}({}), 操作{}.",
                    definition.getPermissionDescription(),
                    permissionsDef,
                    actionsDef);
            }

            List<Permission> permissions = authentication.getPermissions().stream()
                .filter(permission -> {
                    // 未持有任何一个权限
                    if (!permissionsDef.contains(permission.getId())) {
                        return false;
                    }

                    // 未配置 action
                    if (actionsDef.isEmpty()) {
                        return true;
                    }

                    // 已配置 action, 进行校验
                    List<String> actionList = permission.getActions()
                        .stream()
                        .filter(actionsDef::contains)
                        .collect(Collectors.toList());

                    if (actionList.isEmpty()) {
                        return false;
                    }

                    // 如果设定的条件是 OR 则只要满足一个 action 条件即可
                    // 否则过滤结果数量必须和配置的数量相同
                    return logicalIsOr || permission.getActions().containsAll(actionList);
                }).collect(Collectors.toList());
            log.info("permissions: {}", permissions);

            access = logicalIsOr ?
                !CollectionUtils.isEmpty(permissions) :
                //权限数量和配置的数量相同
                permissions.size() == permissionsDef.size();
        }

        // 检查角色
        if (!rolesDef.isEmpty()) {
            if (log.isInfoEnabled()) {
                log.info("检查 角色权限 :{}, definition: {}", rolesDef, definition.getRoles());
            }

            if (authentication.getRole() != null) {
                Function<Predicate<String>, Boolean> func = logicalIsOr
                    ? roleKeys.stream()::anyMatch
                    : roleKeys.stream()::allMatch;

                access = func.apply(role -> rolesDef.equals(role));
            }
        }

        if (!access) {
            throw new AccessDenyException(definition.getMessage());
        }
    }
}
