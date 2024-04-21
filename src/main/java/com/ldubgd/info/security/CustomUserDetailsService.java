package com.ldubgd.info.security;

import com.ldubgd.info.models.Role;
import com.ldubgd.info.models.SystemUsers;
import com.ldubgd.info.repo.SystemUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    SystemUsersRepository usersRepository;

    @Autowired
    public CustomUserDetailsService(SystemUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        SystemUsers user = usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found by email..."));

        return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }



    private Collection<GrantedAuthority> mapRolesToAuthorities (List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
