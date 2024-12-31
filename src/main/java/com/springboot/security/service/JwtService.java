package com.springboot.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtService {
    private String secretKey = "";

    public JwtService() {
        try {
            KeyGenerator sk =KeyGenerator.getInstance("HmacSHA256");
            secretKey= Base64.getEncoder().encodeToString(sk.generateKey().getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String userName) {
        Map<String, Object> claim = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claim)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10))
                .and()
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        byte[] byteKey = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(byteKey);
    }
}
