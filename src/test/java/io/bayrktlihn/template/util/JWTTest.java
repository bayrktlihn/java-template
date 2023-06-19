package io.bayrktlihn.template.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.crypto.SecretKey;

import io.bayrktlihn.template.util.ApplicationProperties;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

class JWTTest {

	@Test
	void generateJwt() {

		Properties applicationProperties = ApplicationProperties.getProperties();

		String base64EncodedJwtKey = (String) applicationProperties.get("jwt.key");

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
		Properties applicationProperties = ApplicationProperties.getProperties();

		String base64EncodedJwtKey = (String) applicationProperties.get("jwt.key");

		byte[] jwtKey = Base64.getDecoder().decode(base64EncodedJwtKey);

		SecretKey secretKey = Keys.hmacShaKeyFor(jwtKey);

		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGloYW4gYmF5cmFrdGFyYSIsImlhdCI6MTY4NzEyMDA3NywiYXVkIjoiYmF5cmt0bGlobiIsImV4cCI6MTY4NzEyMDk3N30.Xn6fJemqwG9EyRBE-ahuMH2zVsk13vlwPmS9fIUEgMo";

		
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
