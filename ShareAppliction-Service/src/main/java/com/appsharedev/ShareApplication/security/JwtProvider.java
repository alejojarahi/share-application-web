package com.appsharedev.ShareApplication.security;

import com.appsharedev.ShareApplication.model.security.Session;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

// This is the principal class of the jwt operations
// This class create the tokens, check the validations and extract the email that is the username for session
@Component
public class JwtProvider {
    private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    // This method generate token with the next information:
    // setSubject: Assign the username of the session
    // setIssuedAt: Assigned the data of created of token
    // setExpiration: Assigned the data of expiration of token
    public String generateToken(Authentication authentication) {
        Session userSession = (Session) authentication.getPrincipal();
        return Jwts.builder().setSubject(userSession.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // This method signature the token with the function setSigningKey
    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    // This method validate the signature of a token and print if have errors in the token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        // This error as print if the token is bad formed
        catch (MalformedJwtException e) {
            logger.error("[Error] Token bad formed " + e.getMessage());
        }
        // This error as print if the token is not support
        catch (UnsupportedJwtException e) {
            logger.error("[Error] Token not support " + e.getMessage());
        }
        // This error as print if the token is expired
        catch (ExpiredJwtException e) {
            logger.error("[Error] Token is experid " + e.getMessage());
        }
        // This error as print if the token is empty
        catch (IllegalArgumentException e) {
            logger.error("[Error] Token is empty " + e.getMessage());
        }
        // This error as print if the token signature have error
        catch (SignatureException e) {
            logger.error("[Error] Token signature have a error " + e.getMessage());
        }
        return false;
    }
}
