package net.uncrash.authorization.api.web;

public interface ParsedToken {

    /**
     * 令牌
     *
     * @return
     */
    String getToken();

    /**
     * 令牌类型
     *
     * @return
     */
    Byte getType();
}
