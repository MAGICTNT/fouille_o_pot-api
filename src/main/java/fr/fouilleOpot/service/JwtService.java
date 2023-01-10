package fr.fouilleOpot.service;


import fr.fouilleOpot.entity.UtilisateurEntity;
import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class JwtService {
    public String generateJwt(String pseudo){
        Set<String> roles = new HashSet<>(
                Arrays.asList("admin","user")
        );
        long duration = System.currentTimeMillis() + 3600;
        System.out.println(duration);
        String jwt = Jwt.issuer("https://example.com/issuer")
                .upn(pseudo)
//                .expiresIn(Duration.ofMinutes(30))
                .expiresIn(duration)
                .groups("admin")
                .sign();
        System.out.println(jwt);
        return jwt;
//        return null;
    }

    public static String getToken(UtilisateurEntity user) {
        return Jwt.issuer("https://example.com/issuer")
                .expiresIn(Duration.ofMinutes(120))
                .upn(user.getPseudo())
                .groups(user.getRole().getLabel_role())
                .sign();
    }

}

