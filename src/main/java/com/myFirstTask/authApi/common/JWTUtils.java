package com.myFirstTask.authApi.common;

import com.myFirstTask.authApi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtils {

    private static String secret = "sample_key";
    private static long expiryDuration = 30 * 60;

    public String generateJwt(User user){

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        // claims
        Claims claims = Jwts.claims()
                .setIssuer(user.getEmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        // optional claims
        claims.put("name", user.getName());
        claims.put("userId", user.getId());
        claims.put("emailId", user.getEmail());

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getName())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean verify(String authorization) {

            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            System.out.println("claims"+claims + claims.isEmpty());
            if(claims.isEmpty()){
                return false;
            }else{
                return true;
            }
    }

    public String extractUserName(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String UserName = (String) claims.get("name");
        System.out.println("UserName : "+UserName);
        return UserName;
    }

//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }

}
