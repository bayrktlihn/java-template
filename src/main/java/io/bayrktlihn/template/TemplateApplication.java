package io.bayrktlihn.template;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.crypto.SecretKey;

import io.bayrktlihn.template.enums.Gender;
import io.bayrktlihn.template.util.ApplicationProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TemplateApplication {
	public static void main(String[] args) {
		Properties applicationProperties = ApplicationProperties.getProperties();

		String base64EncodedJwtKey = (String) applicationProperties.get("jwt.key");

		byte[] jwtKey = Base64.getDecoder().decode(base64EncodedJwtKey);

		SecretKey secretKey = Keys.hmacShaKeyFor(jwtKey);

		Instant now = Instant.now();

		String jwt = Jwts.builder().setSubject("alihan bayraktar").setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(15, ChronoUnit.SECONDS))).signWith(secretKey).compact();

		System.out.println(jwt);
	}
}
