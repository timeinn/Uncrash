package net.uncrash.authorization.basic.jwt;

import io.jsonwebtoken.*;
import net.uncrash.authorization.api.web.ParsedToken;
import net.uncrash.authorization.api.web.TokenParser;
import net.uncrash.core.utils.JSONUtil;
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
            try {
                Claims claims = parse(accessToken);
                if (claims.getExpiration().getTime() <= System.currentTimeMillis()) {
                    return null;
                }
                return JSONUtil.toBean(claims.getSubject(), JwtAuthorizedToken.class);
            } catch (Exception e) {
                logger.debug("parse token [{}] err: {}", accessToken, e.getMessage());
                return null;
            }
        }
        return null;
    }

    public Claims parse(String jwt) throws ExpiredJwtException,
        UnsupportedJwtException, MalformedJwtException,
        SignatureException, IllegalArgumentException {

        SecretKey key = jwtConfig.generalKey();
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
    }
}
