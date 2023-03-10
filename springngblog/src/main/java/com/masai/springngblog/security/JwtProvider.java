package com.masai.springngblog.security;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	private KeyStore keyStore;
	private SecretKey key;
	private String token;

    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    }
    
    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();
    }

	public String getUsernameFromJWT(String jwt) {
		@SuppressWarnings("deprecation")
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(jwt)
				.getBody();
		return claims.getSubject();
	}

	@SuppressWarnings("deprecation")
	public boolean validateToken(String jwt) {
		 try {
		        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
		        return true;
		    } catch (JwtException | IllegalArgumentException e) {
		        // log the exception or do something else
		        return false;
		    }
	}


    

}