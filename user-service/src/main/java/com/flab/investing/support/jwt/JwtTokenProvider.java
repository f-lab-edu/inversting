package com.flab.investing.support.jwt;

import com.flab.investing.global.error.exception.ExpireJwtException;
import com.flab.investing.global.error.exception.InvalidJwtException;
import com.flab.investing.support.security.UserDetailsServiceImpl;
import com.flab.investing.user.application.SessionService;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final SessionService sessionService;
    private final UserDetailsServiceImpl userDetailsService;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.token.access-expiration-time}")
    private long accessExpirationTime;

    @Value("${spring.jwt.token.refresh-expiration-time}")
    private long refreshExpirationTime;

    public String createAccessToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessExpirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpirationTime);

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        sessionService.save(authentication.getName(), refreshToken, refreshExpirationTime);

        return refreshToken;
    }

    public Authentication getAuthentication(String token) {
        String userPrincipal = Jwts.parser().
                setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userPrincipal);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpireJwtException();
        } catch (JwtException e) {
            throw new InvalidJwtException();
        }
    }

}
