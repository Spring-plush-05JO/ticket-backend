package org.example.springplusteam.common.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.domain.user.User;
import org.example.springplusteam.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {


    @Value("${jwt.secret.key}")
    private String secretKey;
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_KEY = "auth";

    private Key key;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] decode = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(decode);
    }

    public String generateToken(AuthUser authUser) {
        Date now = new Date();
        return TOKEN_PREFIX + Jwts.builder()
                .setSubject(authUser.getUsername())
                .claim("id", authUser.getId())
                .claim("Role", authUser.getAuthorities().iterator().next())
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명입니다. ");
            throw new CustomApiException(ErrorCode.INVALID_TOKEN_ERROR);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
            throw new CustomApiException(ErrorCode.INVALID_TOKEN_ERROR);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰입니다.");
            throw new CustomApiException(ErrorCode.INVALID_TOKEN_ERROR);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰입니다. ");
            throw new CustomApiException(ErrorCode.INVALID_TOKEN_ERROR);
        }
    }

    public AuthUser getAuthUserForToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        String id = String.valueOf(claims.getBody().get("id"));
        String email = claims.getBody().getSubject();
        Map<String, String> roleMap = (Map<String, String>) claims.getBody().get("Role");
        UserRole userRole = UserRole.valueOf(roleMap.get("authority").replaceAll("ROLE_", ""));

        log.info("JWT TOKEN CLAIM EMAIL : {}", email);
        User user = User.builder()
                .id(Long.parseLong(id))
                .email(email)
                .userRole(userRole)
                .build();
        return new AuthUser(user);
    }


}
