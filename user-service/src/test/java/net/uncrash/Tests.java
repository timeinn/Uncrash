package net.uncrash;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class Tests {


    public static void main(String[] args) {
        Base64.Encoder encoder = Base64.getEncoder();
        String str = String.format("%-16s", "abcdefghjklnm");
        log.info("str: {}", str);
        byte[] encodeKey = encoder.encode(encoder.encode(str.getBytes()));
        SecretKey secretKey = new SecretKeySpec(encodeKey, SignatureAlgorithm.HS256.getJcaName());

        long ttl = 360000L;
        long now = System.currentTimeMillis();
        Date nowDate = new Date();
        long exprieMillis = now + ttl;
        Date exprie = new Date(exprieMillis);

        JwtBuilder builder = Jwts.builder()
            .setId("u-jwt")
            .setIssuedAt(nowDate)
            .setSubject("abc")
            .signWith(secretKey)
            .setExpiration(exprie);
        String jwtToken = builder.compact();
        log.info("jwt-token: {}", jwtToken);
    }
}
