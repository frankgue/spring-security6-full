package com.gkfcsolution.authorizationjwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Created on 2025 at 13:57
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 01/10/2025
 * @time 13:57
 */
@Component
public class JwtUtils {

    private static final String SECRET_KEY = "PKyiIzWTcs4LFlL6YcJwb94wOBM0hySnbHgjJ6XfIhQi6KdnuezzunwqIjx3lHD5"; // au moins 256 bits

    private static final long ACCESS_TOKEN_EXP_MS = 15 * 60 * 1000; // 15 min
    private static final long REFRESH_TOKEN_EXP_MS = 7 * 24 * 60 * 60 * 1000; // 7 jours

    // Retourne une clé HMAC pour HS256

    //        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String secret = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println("Generated SECRET_KEY: " + secret);
    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ================== Génération de token ==================
    public String generateAccessToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return generateToken(extraClaims, userDetails, ACCESS_TOKEN_EXP_MS);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails, REFRESH_TOKEN_EXP_MS);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expirationMs) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSignInKey())
                .compact();
    }

    // ================== Extraction des claims ==================
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()                  // ✅ Utiliser parserBuilder()
                    .setSigningKey(getSignInKey())       // ✅ Fournir la clé HMAC
                    .build()
                    .parseClaimsJws(token)
                    .getBody();                          // ✅ Claims
        } catch (JwtException e) {
            throw new RuntimeException("JWT invalide ou expiré", e);
        }
    }


    // ================== Validation ==================
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
