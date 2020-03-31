package net.uncrash.authorization.basic.jwt.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.uncrash.core.utils.Serializers;

import java.util.Base64;

@Getter
@Setter
@ToString
public class JWTPayload {

    private String sub;

    private String iss;

    private Long iat;

    private Long exp;

    private Long nbf;

    private String jti;

    public String toBase64() {
        String json = Serializers.toJSON(this);
        return Base64.getEncoder().encodeToString(json.getBytes());
    }
}
