package com.ldubgd.info.repo;

import com.ldubgd.info.models.SystemUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SystemUsersRepository extends JpaRepository<SystemUsers, Long> {

    List<SystemUsers> findBySystemManagers_Id(Long id);

    SystemUsers findBySystemManagers_IdAndId(Long managerId, Long userId);


    Optional<SystemUsers> findByEmail(String email);

    Boolean existsByEmail(String email);


    @Query("SELECT sm.systemManagers.id FROM SystemUsers sm WHERE sm.email = :email")
    Long findSystemManagerIdByUserEmail(@Param("email") String email);


}
