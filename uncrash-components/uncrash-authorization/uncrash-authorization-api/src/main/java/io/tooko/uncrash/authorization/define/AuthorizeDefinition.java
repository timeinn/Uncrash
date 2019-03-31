package io.tooko.uncrash.authorization.define;

import io.tooko.uncrash.authorization.Permission;
import io.tooko.uncrash.authorization.Role;
import io.tooko.uncrash.authorization.User;
import io.tooko.uncrash.authorization.api.web.Authentication;

import java.util.Set;

/**
 * 权限控制定义,定义权限控制的方式
 */
public interface AuthorizeDefinition {

    /**
     * @return 验证时机
     */
    Phased getPhased();

    /**
     * 优先级,如果获取到多个权限控制定义是,则先判断优先级高的
     *
     * @return 优先级
     */
    int getPriority();

    /**
     * @return 要控制的权限
     */
    Set<String> getPermissions();

    String[] getPermissionDescription();

    String[] getActionDescription();

    /**
     * 要控制的权限事件,仅当{@link this#getPermissions()}不为空的时候生效
     *
     * @return 权限事件
     * @see Permission#getActions()
     */
    Set<String> getActions();

    /**
     * 获取完整登陆授权信息
     *
     * @return
     */
    Authentication getAuthentication();

    /**
     * 控制角色访问
     *
     * @return 要控制的角色id集合
     * @see Role#getId()
     */
    Set<String> getRoles();

    /**
     * 控制用户访问
     *
     * @return 要控制的用户id集合
     * @see User#getId()
     */
    Set<String> getUser();

    /**
     * @return 当无权限时, 返回的消息
     */
    String getMessage();

    /**
     * @return 当存在多个配置, 如:配置了多个permission或者actions. 进行判断的逻辑(或者,并且)
     */
    Logical getLogical();

    boolean isEmpty();

}
