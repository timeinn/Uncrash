package io.tooko.uncrash.authorization;

import java.io.Serializable;
import java.util.Set;

public interface Permission extends Serializable {

    String ACTION_QUERY = "query";
    String ACTION_GET = "get";
    String ACTION_ADD = "add";
    String ACTION_UPDATE = "update";
    String ACTION_DELETE = "delete";
    String ACTION_IMPORT = "import";
    String ACTION_EXPORT = "export";
    String ACTION_DISABLE = "disable";
    String ACTION_ENABLE = "enable";

    /**
     * @return 权限唯一 ID
     */
    String getId();

    String getName();

    /**
     * 用户对此权限的可操作事件(按钮)
     * 注意: 任何时候都不应该对返回的 Set 进行写操作
     * @return 如果没有配置返回空 Collections#emptySet(), 不会返回 null.
     */
    Set<String> getActions();

}
