package io.tooko.uncrash.authorization.simple.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.tooko.core.utils.JSONUtil;
import io.tooko.uncrash.authorization.model.AuthenticationBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Long TIMEOUT = 60 * 60 * 24 * 1000L;

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // attempt Authentication when Content-Type is json
        String contentType = request.getContentType();
        if (contentType.equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
            || contentType.equals(MediaType.APPLICATION_JSON_VALUE)) {
            UsernamePasswordAuthenticationToken authRequest = null;
            try {
                AuthenticationBean bean = JSONUtil.toBean(StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8")), AuthenticationBean.class);
                authRequest = bean == null ? this.emptyToken() : new UsernamePasswordAuthenticationToken(bean.getUsername(), bean.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = this.emptyToken();
            }

            setDetails(request, authRequest);
            return this.authenticationManager.authenticate(authRequest);
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
            );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        Claims claims = Jwts.claims();
        claims.put("role", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(auth.getName())
                .setExpiration(new Date(System.currentTimeMillis() + TIMEOUT))
                .signWith(SignatureAlgorithm.HS256, JwtConst.SIGNING_KEY).compact();
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print("{\"token\":\"" + token + "\"}");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UsernamePasswordAuthenticationToken emptyToken() {
        return new UsernamePasswordAuthenticationToken(
            "", "");
    }
}
