package com.ldubgd.info.security;

import com.ldubgd.info.security.filters.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) /*Включає підтримку анотації @Secured (наприклад: @Secured({"ROLE_USER", "ROLE_ADMIN"}))*/
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
//    @Autowired
//    public SecurityConfig(CustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()                                                                                    /*Відкриває методи для відкривання доступів до URL*/
//                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/info").hasRole("ADMIN")
//                .antMatchers("/api/**").authenticated()
                .antMatchers("/secured").authenticated()                                                      /*Воно спочатку зайде в SecurityContextHolder, щоб побачити, чи є там аутентифікований користувач */
                .antMatchers("/info ").authenticated()
                .antMatchers("/admin ").hasRole("ADMIN")
//                .anyRequest().permitAll()
                .anyRequest().authenticated()
                .and()
//                .httpBasic();
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)                             /*Політика безстандартних сесій (кожен запрос як від невідомого користувача)*/
                .and()
                .exceptionHandling()                                                                                    /*Обробка помилок*/
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))                           /*якщо запрос іде без представлення користувача, видає помилку*/
                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);                   /*Spring по класу найде потрібен фільтер і вставить його в наступну чергу*/

        return http.build();
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {                                                      /*Власний провайдер аутентифікації(можна і без нього)*/
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }


    @Bean                                                                                                               /*Центральний обєкт для обробки аутентифікації(вроді можна і без нього)*/
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

}
