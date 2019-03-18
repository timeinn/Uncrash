package io.tooko.uncrash.authorization.annotation;

import io.tooko.uncrash.authorization.define.Phased;
import io.tooko.uncrash.authorization.define.Logical;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authorize {

    /**
     * 对角色授权,当使用按角色授权时，对模块以及操作级别授权方式失效
     * @return roleId
     */
    String[] role() default {};

    /**
     * 对模块授权
     * @return
     */
    String[] permission() default {};

    /**
     * 对操作授权
     * 如 增删改查等
     * @return action array
     */
    String[] action() default {};

    /**
     * 验证是否指定的 user
     * @return username array
     */
    String[] user() default {};

    /**
     * 验证授权失败时返回的消息内容
     * @return
     */
    String message() default "unauthorized";

    /**
     * 合并类上的注解
     * @return
     */
    boolean merge() default true;

    /**
     * 验证模式，在使用多个验证条件时有效
     * @return logical
     */
    Logical logical() default Logical.DEFAULT;

    /**
     * 验证时机，在方法调用前还是调用后
     * @return
     */
    Phased phased() default Phased.before;

    /**
     * 是否忽略，忽略将不进行权限控制
     * @return
     */
    boolean ignore() default false;

    String[] description() default {};

    /**
     * 验证模式，在使用多个验证条件时有效
     *
     * @return
     */
    MOD mod() default MOD.OR;

    enum MOD {
        /**
         * 并集，需要满足所有验证条件
         */
        AND,
        /**
         * 交集，满足其中一个验证条件
         */
        OR
    }
}
