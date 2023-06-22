package io.bayrktlihn.template.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

class JWTTest {

    @Test
    void generateJwt() {

        ApplicationProperties applicationProperties = ApplicationProperties.get();

        String base64EncodedJwtKey = applicationProperties.getValue("jwt.key", String.class);

        byte[] jwtKey = Base64.getDecoder().decode(base64EncodedJwtKey);

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtKey);

        Instant now = Instant.now();

        String jwt = Jwts.builder()
                .setSubject("alihan bayraktar")
                .setIssuedAt(Date.from(now))
                .setAudience("bayrktlihn")
                .setExpiration(Date.from(now.plus(15, ChronoUnit.MINUTES))).signWith(secretKey).compact();

        System.out.println(jwt);

    }

    @Test
    void validateJwt() {
        ApplicationProperties applicationProperties = ApplicationProperties.get();

        String base64EncodedJwtKey = applicationProperties.getValue("jwt.key", String.class);

        byte[] jwtKey = Base64.getDecoder().decode(base64EncodedJwtKey);

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtKey);

        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGloYW4gYmF5cmFrdGFyIiwiaWF0IjoxNjg3NDUyMzk3LCJhdWQiOiJiYXlya3RsaWhuIiwiZXhwIjoxNjg3NDUzMjk3fQ.UnfZmNgWVokAcZQsswLudl3e3bzdhtM1m_CVZHeWgbU";


        try {
            Jws<Claims> jwsClaims = Jwts.parserBuilder()
                    .requireAudience("bayrktlihn")
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt);

            Claims claims = jwsClaims.getBody();

            System.out.println(claims.getSubject());
        } catch (ExpiredJwtException e) {
            System.out.println("Expirated jwt exception");
        } catch (SignatureException e) {
            System.out.println("Signature exception");
        }


    }
}
