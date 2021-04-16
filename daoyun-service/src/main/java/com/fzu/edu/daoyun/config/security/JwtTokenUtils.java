package com.fzu.edu.daoyun.config.security;

import com.fzu.edu.daoyun.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    private static final String CLAIM_KEY_PHONENUMBER="sub";
    private static final String CLAIM_KEY_CREATED_TIME="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(User user){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_PHONENUMBER,user.getPhoneNumber());
        claims.put(CLAIM_KEY_CREATED_TIME,new Date());
        return generateToken(claims);
    }

    public String getPhoneNumberFromToken(String token){
        String phoneNumber;
        try {
            Claims claims=getClaimsFromToken(token);
            phoneNumber=claims.getSubject();
        } catch (Exception e) {
            phoneNumber=null;
        }
        return phoneNumber;
    }

    public boolean validateToken(String token, User user){
        String phoneNumber=getPhoneNumberFromToken(token);
        return phoneNumber.equals(user.getPhoneNumber()) && isTokenExpired(token);
    }

    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    public String refreshToken(String token){
        Claims claims=getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED_TIME,new Date());
        return generateToken(claims);
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate= getExpiredDateFromToken(token);
        System.out.println(expiredDate);
        return expiredDate.after(new Date());
    }

    private Date getExpiredDateFromToken(String token) {
        Claims claims=getClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
