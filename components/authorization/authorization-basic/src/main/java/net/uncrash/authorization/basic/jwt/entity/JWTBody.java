package net.uncrash.authorization.basic.jwt.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.uncrash.core.utils.DecriptUtil;

@Getter
@Setter
@ToString
public class JWTBody {

    private JWTHeader header;

    private JWTPayload payload;

    private String secret;

    public String signature() {
        String jwtString = new StringBuilder()
                .append(header.toBase64())
                .append(payload.toBase64())
                .toString();

        return DecriptUtil.sha1(jwtString);
    }
}
