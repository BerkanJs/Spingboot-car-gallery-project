package com.galleryProject.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Base64.Decoder;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {
    public static final String SECRET_KEY="6cYJXu8stPkDVjECXe8pgLI0612LApCzNEd/ciEWJjo=";
    public String generateToken(UserDetails userDetails){
       return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();



    
    }

    public <T> T exportToken(String token , Function<Claims,T> claimsFunc){
        Claims claims = getClaims(token);
        return claimsFunc.apply(claims);
    }



    public Claims getClaims(String token){
       Claims claims= Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token).getBody();
        return claims;

    }

    public String getUsernameByToken(String token){
      return  exportToken(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token){
        Date expireDate=exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);
    }

    public Key getKey(){
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

}
