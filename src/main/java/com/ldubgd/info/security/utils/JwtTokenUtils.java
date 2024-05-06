package com.ldubgd.info.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}") /*перенесено з application.properties(секретний ключ)*/
    private String secret;

    @Value("${jwt.lifetime}") /*Тривалість роботи JWT*/
    private Duration jwtLifeTime;




    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("roles", roleList);

        Date issuedData = new Date();
        Date expiredDate = new Date(issuedData.getTime() + jwtLifeTime.toMillis());

        return Jwts.builder()
                .setClaims(claims) /*Там ролі користувача*/
                .setSubject(userDetails.getUsername()) /*в моєму випадку там email*/
                .setIssuedAt(issuedData) /*час створення*/
                .setExpiration(expiredDate) /*час завершення токена*/
                .signWith(SignatureAlgorithm.HS256, secret) /*алгоритм і секретний ключ*/
                .compact();


    }


    public String getEmail(String token){             /*написав замість getUsername*/
        return getAllClaimsFromToken(token).getSubject();/*getSubject(), томущо ми знаєм, що прописали до нього (.setSubject(userDetails.getUsername())) ВИЩЕ...*/
    }


    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class); /*так як ми його і записували у вигляді List<String>*/
    }



    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)  /*Дуже прікольно, що тут одразу відбувається перевірка токена*/
                .getBody(); /*Отримання інформації про користувача, яка зашита в токені*/
    }


}
