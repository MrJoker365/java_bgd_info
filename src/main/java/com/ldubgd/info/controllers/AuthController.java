package com.ldubgd.info.controllers;

import com.ldubgd.info.dto.LoginDto;
import com.ldubgd.info.dto.RegisterDto;
import com.ldubgd.info.models.Role;
import com.ldubgd.info.models.SystemUsers;
import com.ldubgd.info.repo.RoleRepository;
import com.ldubgd.info.repo.SystemUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {


    private AuthenticationManager authenticationManager;
    private SystemUsersRepository systemUsersRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired

    public AuthController(AuthenticationManager authenticationManager, SystemUsersRepository systemUsersRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.systemUsersRepository = systemUsersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }




    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterDto registerDto) {

        if (systemUsersRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email are using ", HttpStatus.BAD_REQUEST);
        }


        SystemUsers users = new SystemUsers();
        users.setEmail(registerDto.getEmail());
        users.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleRepository.findByName("ADMIN").get();
        users.setRoles(Collections.singletonList(role));

        systemUsersRepository.save(users);

        return new ResponseEntity<>("User registered success", HttpStatus.OK);

    }




//    @PostMapping("/login")/*Як воно працює???. Чому по логічному, не спрацьовує return коли неправильна аутентифікація???*/
//    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getEmail(),
//                        loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication); /*Глянути як тут працює...*/
//        return new ResponseEntity<>("User signed success!", HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User signed in successfully!", HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }




}
