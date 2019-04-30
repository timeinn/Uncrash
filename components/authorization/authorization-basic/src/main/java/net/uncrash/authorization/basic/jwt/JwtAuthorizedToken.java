package net.uncrash.authorization.basic.jwt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.uncrash.authorization.api.dict.TokenTypeEnum;
import net.uncrash.authorization.api.web.AuthorizedToken;

/**
 * @author Sendya
 */
public class JwtAuthorizedToken implements AuthorizedToken {

    public static final Byte TOKEN_TYPE = TokenTypeEnum.JWT.getValue();

    private String userId;

    private Integer type;

    private Integer maxInactiveInterval;

    public JwtAuthorizedToken() {
    }

    public JwtAuthorizedToken(String userId) {
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    @JsonIgnore
    public String getToken() {
        return null;
    }

    @Override
    public Byte getType() {
        return TOKEN_TYPE;
    }
}
