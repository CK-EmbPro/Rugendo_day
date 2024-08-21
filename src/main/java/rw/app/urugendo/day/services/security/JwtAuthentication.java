package rw.app.urugendo.day.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.models.usermanagement.User;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtAuthentication {
    @Value("${token.key}")
    private  String SECRET_KEY;
    private Claims extractAllClaims(String token){
            return Jwts
                    .parserBuilder()
                    .setSigningKey(signInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(Map<String, Object> extraClaims, User user){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(signInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean IsTokenExpired(String token){
        Date expirationDate = extractClaim(token,Claims::getExpiration);
        return expirationDate.after(new Date(System.currentTimeMillis()));
    }
    public String getUniqueIdentifier(String token){
        return extractClaim(token,Claims::getSubject);
    }

    private Key signInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
