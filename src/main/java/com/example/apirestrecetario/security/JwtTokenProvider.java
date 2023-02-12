package com.example.apirestrecetario.security;

import com.example.apirestrecetario.model.Cocinero;
import com.example.apirestrecetario.service.CocineroDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private static String jwtSecret = "TExBVkVfTVVZX1NFQ1JFVEE=";

    private int jwtExpirationInMs = 86400000;

    @Autowired
    private CocineroDetailsService cocineroDetailsService;

    public String generateToken(Cocinero cocinero) {
        Claims claims = Jwts.claims().setSubject(cocinero.getEmail());
        claims.put("nombre", cocinero.getNombre());
        claims.put("apellido", cocinero.getApellido());
        claims.put("telefono", cocinero.getTelefono());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public static String getUsername(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        String username = JwtTokenProvider.getUsername(token);
        UserDetails userDetails = cocineroDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }




}
