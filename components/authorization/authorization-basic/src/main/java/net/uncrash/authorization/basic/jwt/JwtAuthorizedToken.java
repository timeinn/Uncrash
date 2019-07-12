package net.uncrash.authorization.basic.jwt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.uncrash.authorization.api.dict.TokenTypeEnum;
import net.uncrash.authorization.api.web.AuthorizedToken;

/**
 * @author Sendya
 */
public class JwtAuthorizedToken implements AuthorizedToken {

    public static final Byte TOKEN_TYPE = TokenTypeEnum.JWT.getValue();

    private String id;

    private Integer type;

    public JwtAuthorizedToken() {
    }

    public JwtAuthorizedToken(String userId) {
        this.id = userId;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
