package com.ldubgd.info.repo;

import com.ldubgd.info.models.InfoTables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InfoTablesRepository extends JpaRepository<InfoTables, Long> {
    @Query("SELECT t FROM InfoTables t WHERE t.tableName = :tableName")
    InfoTables findByTableName(@Param("tableName") String tableName);

    @Query("select t.tableName from InfoTables t where t.systemManagers.id = :systemManagersId")
    List<String> findTableNameBySystemManagersId(@Param("systemManagersId") Long systemManagersId);

//    InfoTables getByTableName(String tableName);

}
