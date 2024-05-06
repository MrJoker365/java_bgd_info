package com.ldubgd.info.repo;

import com.ldubgd.info.models.SystemUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SystemUsersRepository extends JpaRepository<SystemUsers, Long> {

    List<SystemUsers> findBySystemManagers_Id(Long id);

    SystemUsers findBySystemManagers_IdAndId(Long managerId, Long userId);


    Optional<SystemUsers> findByEmail(String email);

    Boolean existsByEmail(String email);



}
