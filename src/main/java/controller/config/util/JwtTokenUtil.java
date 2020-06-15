package controller.config.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenUtil implements Serializable {

    private  static final String KEY = "ak.tocxd1_!23+ldm";
    private static final int TOKEN_TIME = 60 * 60 * 24 * 1000; // Un dia en milisegundos

    public <T> T extractJwt(String token, Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractJwt(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractJwt(token, Claims::getExpiration);
    }

    private Boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private String generateToken(Map<String, Object> claims, String user){
        return Jwts.builder().setClaims(claims).setSubject(user)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_TIME))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public String createJwt(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return generateToken(claims, userDetails.getUsername());
    }

    public Boolean isValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isExpired(token));
    }

}
