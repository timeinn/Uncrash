package net.uncrash.authorization.basic.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.uncrash.authorization.api.dict.TokenTypeEnum;
import net.uncrash.authorization.api.web.Authentication;
import net.uncrash.authorization.api.web.GeneratedToken;
import net.uncrash.authorization.api.web.TokenGenerator;
import net.uncrash.core.utils.JSONUtil;
import net.uncrash.core.utils.id.IDGenerator;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenGenerator implements TokenGenerator {

    private JwtConfig jwtConfig;

    public JwtTokenGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Byte getSupportTokenType() {
        return TOKEN_TYPE;
    }

    @Override
    public GeneratedToken generate(Authentication authentication) {
        String userId = authentication.getUser().getId();
        String subject = JSONUtil.toJSON(new JwtAuthorizedToken(userId));
        String jwtToken = createJWT(jwtConfig.getId(), subject, jwtConfig.getTtl());

        int timeout = jwtConfig.getTtl();

        return new GeneratedToken() {

            private static final long serialVersionUID = -6871107326329947052L;

            @Override
            public Map<String, Object> getResponse() {
                Map<String, Object> data = new HashMap<>();
                data.put("token", jwtToken);
                data.put("timeout", timeout);
                data.put("type", TokenTypeEnum.JWT);
                return data;
            }

            @Override
            public String getToken() {
                return jwtToken;
            }

            @Override
            public Byte getType() {
                return TOKEN_TYPE;
            }

            @Override
            public int getTimeout() {
                return timeout;
            }
        };
    }

    private String createJWT(String id, String subject, long ttl) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        SecretKey key = jwtConfig.generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(nowDate)
                .setSubject(subject)
                .signWith(key);
        if (ttl >= 0) {
            long exprieMillis = now + ttl;
            Date exprie = new Date(exprieMillis);
            builder.setExpiration(exprie);
        }
        return builder.compact();
    }
}
