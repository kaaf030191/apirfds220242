package com.codideep.main.Helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
	private static final String SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256).toString();
	private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	private boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}

	public String generateToken(String idUser, String firstName) {
		Map<String, Object> claims = new HashMap<>();
        claims.put("idUser", idUser);
        claims.put("firstName", firstName);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
			.signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
			.compact();
	}

	public Claims extractClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(SIGNING_KEY)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public String extractId(String token) {
		return (String) extractClaims(token).get("idUser");
	}

	public boolean isTokenValid(String token, String idUser) {
		return extractId(token).equals(idUser) && !isTokenExpired(token);
	}
}