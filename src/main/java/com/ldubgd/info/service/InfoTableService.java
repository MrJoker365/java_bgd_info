package com.ldubgd.info.service;

import com.ldubgd.info.models.InfoTables;
import com.ldubgd.info.repo.InfoTablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoTableService implements InfoTableServiceInterface {

    @Autowired
    private InfoTablesRepository infoTablesRepository;

    @Override
    public InfoTables createTable(InfoTables infoTables) {
        return infoTablesRepository.save(infoTables);
    }

    @Override
    public InfoTables getAllTableInfoByTableName(String tableName) {
        return infoTablesRepository.findByTableName(tableName);
    }

    @Override
    public InfoTables getAllTableInfoById(Long id) {
        return infoTablesRepository.findById(id).orElse(null);
    }


    @Override
    public List<String> getAllTableNameBySys_manager_id(Long id) {
        return infoTablesRepository.findTableNameBySystemManagersId(id);
    }


}
