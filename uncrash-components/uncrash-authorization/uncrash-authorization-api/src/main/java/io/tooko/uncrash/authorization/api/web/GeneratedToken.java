package io.tooko.uncrash.authorization.api.web;

import java.io.Serializable;
import java.util.Map;

public interface GeneratedToken extends Serializable {

    /**
     * 响应的数据, 可自定义数据给调用者
     * @return
     */
    Map<String, Object> getResponse();

    /**
     * @return 令牌字符串, 令牌具有唯一性, 不可逆, 不包含敏感信息
     */
    String getToken();

    /**
     * @return 令牌类型
     */
    Byte getType();

    /**
     * @return 令牌有效期 (单位毫秒)
     */
    int getTimeout();
}
