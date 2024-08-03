package com.eaglestalwart.MailTransfer.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.function.Function;

public class JwtUtil {

    private static final String SECRET_KEY = "secret";

    public static String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Boolean validateToken(String token, String email){
        final String username = extractEmail(token);
        return (username.equals(email) && !isTokenExpired(token));
    }

    public static String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getBody();
    }

    private static Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
}
