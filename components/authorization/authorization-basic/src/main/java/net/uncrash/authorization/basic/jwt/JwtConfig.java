package net.uncrash.authorization.basic.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JwtConfig {

    private static Base64.Encoder encoder = Base64.getEncoder();

    private String id;

    private String secret;

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
        if (secret.length() < 16) {
            secret = String.format("%-16s", secret);
        }
        byte[] encodeKey = encoder.encode(encoder.encode(secret.getBytes()));
        return new SecretKeySpec(encodeKey, SignatureAlgorithm.HS256.getJcaName());
//         return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
