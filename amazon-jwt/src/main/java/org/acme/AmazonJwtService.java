package org.acme;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;

import io.smallrye.jwt.build.Jwt;

@Singleton
public class AmazonJwtService {
    
    public String generateJwt(){
        Set<String> roles = new HashSet<>(
            Arrays.asList("admin", "writer")
        );
       return Jwt.issuer("amazon-jwt")
                    .subject("amazon-jwt") 
                    .groups(roles)
                    .expiresAt(
                        System.currentTimeMillis() + 3600
                    )
                    .sign();
    }
}
