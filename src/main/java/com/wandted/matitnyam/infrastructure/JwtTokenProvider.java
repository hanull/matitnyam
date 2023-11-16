package com.wandted.matitnyam.infrastructure;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.wandted.matitnyam.domain.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final long validityTime;

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") final String key,
                            @Value("${security.jwt.token.expire-length}") final long validityTime) {
        this.key = Keys.hmacShaKeyFor(key.getBytes(UTF_8));
        this.validityTime = validityTime;
    }

    public String createToken(final long memberId, final MemberRole role) {
        final Map<String, ?> claims = Map.of("memberId", memberId, "role", role.name());
        final Date now = new Date();
        final Date expireTime = new Date(now.getTime() + validityTime);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(expireTime)
                .signWith(key, SIG.HS256)
                .compact();
    }

    public boolean isValid(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return claims.getPayload().getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
}
