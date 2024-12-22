package com.hakan.api.jwt;

import com.hakan.api.entity.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    private static String secretKey;
    private static final long EXPIRATION_TIME = 86400000; // 24 saat (milisaniye)

    // Setter ile static değişkene değer atama
    @Value("${jwt.secret}")
    public void setSecretKey(String secretKey) {
        JwtUtils.secretKey = secretKey;
    }

    public static String generateToken(Long appUserId, String appUserName) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes()); // Anahtarın doğru şekilde şifrelenmesi için
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", appUserId);

        // JWT oluşturma ve imzalama
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(appUserName)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));

        // Token'ı imzalamak için SignatureAlgorithm kullanıyoruz
        return jwtBuilder.signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public static Long extractId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class); // ID'yi döndürüyoruz
    }

    public static String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public static boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims getClaims(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes()); // Anahtar

        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key) // Anahtar kullanılarak doğrulama yapılacak
                .build();

        return (Claims) jwtParser.parse(token).getBody(); // Claim'leri döndür
    }

    public static AppUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((AppUser) principal);
    }

}
