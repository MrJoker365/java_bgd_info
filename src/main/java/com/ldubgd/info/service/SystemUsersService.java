package com.ldubgd.info.service;

import com.ldubgd.info.models.SystemUsers;
import com.ldubgd.info.repo.SystemUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
