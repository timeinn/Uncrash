package io.tooko.uncrash.authorization.api.web;

public interface AuthorizedToken extends ParsedToken {

    /*

    {
        "sub": "1",
        "iss": "http://localhost:8000/auth/login",
        "iat": 1451888119,
        "exp": 1454516119,
        "nbf": 1451888119,
        "jti": "37c107e4609ddbcc9c096ea5ee76c667"
    }

    sub: 该JWT所面向的用户
    iss: 该JWT的签发者
    iat(issued at): 在什么时候签发的token
    exp(expires): token什么时候过期
    nbf(not before)：token在此时间之前不能被接收处理
    jti：JWT ID为web token提供唯一标识

     */


    /**
     * 令牌绑定的用户 ID
     * @return long
     */
    Long getUserId();

    /**
     * @return 令牌有效期，单位毫秒，-1为长期有效
     */
    default long getMaxInactiveInterval() {
        return -1;
    }

}
