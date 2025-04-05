package com.Backend.backend.utils;

import java.security.Signature;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.security.Key;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
    
    private String secretKey = "your-secret-key"; // Replace with your actual secret key

    public String genarateToken(UserDetails userDetails){
        return genarateToken(new HashMap<>(),userDetails);
    }

    
    private String genarateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSigninKey() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode("345DG3434DHN");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims:: getSubject);
    }
    
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
