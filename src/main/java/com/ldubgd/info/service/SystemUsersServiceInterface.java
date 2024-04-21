package com.ldubgd.info.service;

import com.ldubgd.info.models.SystemUsers;

import java.util.List;

public interface SystemUsersServiceInterface {
    public SystemUsers saveUsers(SystemUsers users);
    public List<SystemUsers> getAllUsers();
    public List<SystemUsers> getAllUsers(Long id);

    public SystemUsers getUser(Long managerId, Long userId);
}
