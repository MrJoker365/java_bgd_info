package com.ldubgd.info.service;

import com.ldubgd.info.models.SystemUsers;
import com.ldubgd.info.repo.SystemUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemUsersService implements SystemUsersServiceInterface {



    @Autowired
    private SystemUsersRepository systemUsersRepository;


    @Override
    public SystemUsers saveUsers(SystemUsers users) {
        return systemUsersRepository.save(users);
    }

    @Override
    public List<SystemUsers> getAllUsers() {
        return systemUsersRepository.findAll();
    }

    @Override
    public List<SystemUsers> getAllUsers(Long id) {
        return systemUsersRepository.findBySystemManagers_Id(id);
    }

    @Override
    public SystemUsers getUser(Long managerId, Long userId) {
        return systemUsersRepository.findBySystemManagers_IdAndId(managerId, userId);
    }




    /*ТАКИЙ САМИ МЕТОД Я ВИКОРИСТОВУЮ У security.CustomUserDetailsService  */ ///////////////////////////////////////////////////////////

//    public UserDetails loadUserByEmail (String email) throws UsernameNotFoundException {
//        SystemUsers user = systemUsersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
//                String.format("Користувача '%s' не найдено", email)
//        ));
//
//        return new User(
//                user.getEmail(),
//                user.getPassword(),
//                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
//    }

    /*ТАКИЙ САМИ МЕТОД Я ВИКОРИСТОВУЮ У security.CustomUserDetailsService  */ ////////////////////////////////////////////////////////////

}
