package com.example.demo.component;



import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
     
    // previous code is not shown...
     
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
     private static final String SECRET_KEY = "8f5fd5a2-f9f8-49ff-940f-9dd6386bee15";
    public boolean validateAccessToken(String token) {
        try {
        	Key hmacKey = new SecretKeySpec(SECRET_KEY.getBytes(), 
                    SignatureAlgorithm.HS256.getJcaName());
        	Jwts.parser().setSigningKey(hmacKey).parseClaimsJws(token);
            return true;
        }  catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        }
        catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        }catch(SignatureException ex) {
        	LOGGER.error("signature did not match", ex);
        }
         
        return false;
    }
     
    public String getSubject(String token) {
    	Claims claims = getClaims(token);
        return claims.getSubject();
    }
    
    public String getCustomInfo(String token) {
    	Claims claims = getClaims(token);
        return (String)claims.get("custominfo");
    }
    
    private Claims getClaims(String token) {
    	Claims claims = parseClaims(token);
		return claims;
    }
     
    private Claims parseClaims(String token) {
    	Key hmacKey = new SecretKeySpec(SECRET_KEY.getBytes(), 
                SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parser()
                .setSigningKey(hmacKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
