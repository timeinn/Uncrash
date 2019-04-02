package net.uncrash.authorization.simple.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String HEADER = "Authorization";

    private static final String PREFIX = "Bearer";

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(HEADER);
        if (token == null || !token.startsWith(PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String result = token.replace(PREFIX, "").trim();
        Claims claims = Jwts.parser()
            .setSigningKey(JwtConst.SIGNING_KEY)
            .parseClaimsJws(result)
            .getBody();
        String user = claims.getSubject();
        @SuppressWarnings("unchecked")
        List<String> roles = claims.get("roles", List.class);
        List<SimpleGrantedAuthority> auth = roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, auth);
        }
        return null;
    }
}
