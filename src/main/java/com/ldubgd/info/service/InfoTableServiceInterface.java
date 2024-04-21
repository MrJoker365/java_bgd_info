package com.ldubgd.info.service;

import com.ldubgd.info.models.InfoTables;

import java.util.List;

public interface InfoTableServiceInterface {

    public InfoTables createTable(InfoTables infoTables);
    public InfoTables getAllTableInfoByTableName(String tableName);

    public InfoTables getAllTableInfoById (Long id);

    public List<String> getAllTableNameBySys_manager_id(Long id);
}
