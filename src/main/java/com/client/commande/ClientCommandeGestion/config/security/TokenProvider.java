package com.client.commande.ClientCommandeGestion.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by asy on 20/10/2017.
 */
@Component
public class TokenProvider {
    private String secretKey ="la-cle-tres-tres-secrete";

    private Long tokenValidityInMilliseconds ;

    private static final String AUTHORITIES_KEY = "auth";

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);


    @PostConstruct
    public void init() {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.tokenValidityInMilliseconds =  ((Integer)(1000 * 36000)).longValue();
    }

    public String createToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.tokenValidityInMilliseconds);

        String authorities = authentication.getAuthorities().stream().map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(authentication.getName())
                .setIssuedAt(now).signWith(SignatureAlgorithm.HS512, this.secretKey)
                .setExpiration(validity).claim(AUTHORITIES_KEY,authorities).compact();
    }

    public Authentication getAuthentication(String token) {

        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
                .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature: " + e.getMessage());
            return false;
        }
    }
}
