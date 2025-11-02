package com.laboratoriodecodigo.configuracion;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    // Mantenemos estas propiedades, pero ahora se inicializarán en el constructor.
    private final JwtParser jwtParser;
    private final Key signingKey;
    private final int jwtExpirationMs;

    // CONSTRUCTOR CORREGIDO: SOLO RECIBE STRINGS DEL ARCHIVO DE PROPIEDADES
    public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret,
                            @Value("${app.jwtExpirationMs}") int jwtExpirationMs) {

        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        this.jwtParser = Jwts.parser()
                .setSigningKey(this.signingKey)
                .build();
        this.jwtExpirationMs = jwtExpirationMs;
    }


    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            jwtParser.parse(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Token JWT inválido: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Token JWT expirado: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Token JWT no soportado: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("La cadena JWT está vacía: {}", ex.getMessage());
        } catch (SignatureException ex) {
            logger.error("Firma JWT no válida: {}", ex.getMessage());
        }
        return false;
    }
}