package es.aboris.crud.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class JWTUtils {

    private static final long endNano = 15 * 60 * 1000;
    private static final String secret = "aboris";

    public static String getToken(String username){
        List grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("user")
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ZonedDateTime dateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());

        String token = Jwts
                .builder()
                .setId("crud")
                .setSubject(username)
                .claim("authorities", grantedAuthorities)
                .setIssuedAt(Date.from(dateTime.toInstant()))
                .setExpiration(Date.from(dateTime.plusHours(2).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

        return token;
    }

    public static Claims validateToken(String token){
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }
}
