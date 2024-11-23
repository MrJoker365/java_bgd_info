package com.ldubgd.info.repo;

import com.ldubgd.info.models.SystemManagers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SystemManagersRepository extends JpaRepository<SystemManagers, Long> {

//    Long findIdByEmail(String email);
    @Query("SELECT sm.id FROM SystemManagers sm WHERE sm.email = :email")
    Long findIdByEmail(@Param("email") String email);

    SystemManagers findByEmail(String email);
}
