package com.ldubgd.info.repo;

import com.ldubgd.info.models.TableColumns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableColumnsRepository extends JpaRepository<TableColumns, Long> {
//    List<TableColumns> getAllByInfoTablesWhere

    List<TableColumns> findByInfoTables_TableName(String tableName);
//    TableColumns findByInfoTables_TableNameAndAndColumnId(String tableName, Long columnId);




}

