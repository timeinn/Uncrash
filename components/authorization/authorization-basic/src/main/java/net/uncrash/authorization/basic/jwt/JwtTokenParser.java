package net.uncrash.authorization.basic.jwt;

import io.jsonwebtoken.*;
import net.uncrash.authorization.api.web.ParsedToken;
import net.uncrash.authorization.api.web.TokenParser;
import net.uncrash.core.utils.Serializers;
import net.uncrash.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

/**
 * 令牌解析器，用于在接受到请求到时候，从请求中获取令牌
 */
public class JwtTokenParser implements TokenParser {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenParser.class);

    private JwtConfig jwtConfig;

    public JwtTokenParser(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public ParsedToken parseToken(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String accessToken = request.getHeader("Access-Token");
        if (StringUtils.isEmpty(accessToken)) {
            accessToken = request.getHeader("Authorization");
            if (!StringUtils.isEmpty(accessToken)) {
                if (accessToken.contains(" ")) {
                    String[] auth = accessToken.split("[ ]");
                    if (auth[0].equalsIgnoreCase("jwt") || auth[0].equalsIgnoreCase("Bearer")) {
                        accessToken = auth[1];
                    } else {
                        return null;
                    }
                }
            }
        }
        if (accessToken != null) {
            return parseJwtBean(accessToken);
        }
        return null;
    }

    @Override
    public ParsedToken parseJwt(String jwtToken) {
        return parseJwtBean(jwtToken);
    }

    private JwtAuthorizedToken parseJwtBean(String jwtToken) {
        try {
            Claims claims = parse(jwtToken);
            if (claims.getExpiration().getTime() <= System.currentTimeMillis()) {
                return null;
            }
            return Serializers.toBean(claims.getSubject(), JwtAuthorizedToken.class);
        } catch (Exception e) {
            logger.error("parseJWT [{}] err: {}", jwtToken, e.getMessage());
        }
        return null;
    }

    private Claims parse(String jwt) throws ExpiredJwtException,
        UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {

        SecretKey key = jwtConfig.generalKey();

        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
    }
}
