package net.uncrash.authorization.basic.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JwtConfig {

    private String id = "uncrash-jwt";

    private String secret = Base64.getEncoder().encodeToString("uncrash.jwt.secret".getBytes());

    private int ttl = 60 * 60 * 1000;

    private int refreshTtl = 12 * 60 * 60 * 1000;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getRefreshTtl() {
        return refreshTtl;
    }

    public void setRefreshTtl(int refreshTtl) {
        this.refreshTtl = refreshTtl;
    }

    public SecretKey generalKey() {
        /*byte[] encodeKye = Base64.getDecoder().decode(secret);
        return new SecretKeySpec(encodeKye, 0, encodeKye.length, SignatureAlgorithm.HS256.getValue());*/
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
