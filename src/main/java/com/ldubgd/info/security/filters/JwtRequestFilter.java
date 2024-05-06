package com.ldubgd.info.security.filters;

import com.ldubgd.info.security.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j /*логування*/
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); /*посуті перевірка чи звертається авторизований користувач*/
        String email = null;
        String jwt = null;


        /* "Bearer "  --  так починається сам JWT token */
        /* Тому потім .substring(7), щоб відділити це слово і отримати сам токен */
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);

            try {
                email = jwtTokenUtils.getEmail(jwt); /* наш метод вже має перевірку коректності самого токена*/
            } catch (ExpiredJwtException e) {
                log.debug("Сплинув час роботи токена");
            } catch (SignatureException e) {
                log.debug("Підпис токена не вірний, або кимось замінений");
            }
        }




        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);

            /* Є проблема такого підходу:
            *   тут немає звірки даних токена з даними в БД, так як вважається що є довіра до токена і його даних
            *   і відповідно, якщо виставлено сесію токена допустим на тиждень, а даного користувача вже немає в БД
            *   Виникнуть проблеми....*/
        }
        filterChain.doFilter(request, response);

    }
}




//package com.ldubgd.info.security.filters;
//
//public class JwtRequestFilter {
//
//}