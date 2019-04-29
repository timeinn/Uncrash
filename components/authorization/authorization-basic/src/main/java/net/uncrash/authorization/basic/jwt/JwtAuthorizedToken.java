package net.uncrash.authorization.basic.jwt;
import net.uncrash.authorization.api.dict.TokenTypeEnum;
import net.uncrash.authorization.api.web.AuthorizedToken;

/**
 * @author Sendya
 */
public class JwtAuthorizedToken implements AuthorizedToken {

    public static final Byte TOKEN_TYPE = TokenTypeEnum.JWT.getValue();

    private String token;

    private String userId;

    private Integer type;

    private Integer maxInactiveInterval;

    public JwtAuthorizedToken() {
    }

    public JwtAuthorizedToken(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Byte getType() {
        return TOKEN_TYPE;
    }
}
