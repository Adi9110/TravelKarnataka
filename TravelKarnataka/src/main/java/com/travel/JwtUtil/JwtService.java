package com.travel.JwtUtil;



//Creates, signs, and validates JWT tokens.
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.awt.RenderingHints.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
  public static final String SECRET = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";

  public String generateToken(String email) {
      String token= Jwts.builder()
              .setSubject(email)
              .setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
              .signWith(SignatureAlgorithm.HS256, SECRET)
              .compact();
      System.out.println("Generated JWT: " + token); 
return token;






  }
  public boolean isTokenValid(String token, UserDetails userDetails) {
      String email = extractUsername(token);
      return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }


  public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
  }

  private boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      // Make sure to use the correct parser for URL-safe Base64 encoding
      Claims claims = Jwts.parserBuilder()
              .setSigningKey(SECRET) // Make sure the secret key is the same as when generating
              .build()
              .parseClaimsJws(token) // Parse the JWT token
              .getBody(); // Get the body (claims) of the JWT

      return claimsResolver.apply(claims); // Extract the requested claim
  }

}


