package com.ldubgd.info.service;

//import com.ldubgd.info.models.Main_info;
//import com.ldubgd.info.repo.Main_infoRepository;
import com.ldubgd.info.models.InfoTables;
import com.ldubgd.info.models.TableColumns;
import com.ldubgd.info.repo.InfoTablesRepository;
import com.ldubgd.info.repo.TableColumnsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Main_infoService /*implements Main_infoServiceInterface*/{


//    @Autowired
//    private Main_infoRepository mainInfoRepository;
//
//    @Override
//    public Main_info saveInfo(Main_info mainInfo) {
//        return mainInfoRepository.save(mainInfo);
//    }
//
//    @Override
//    public List<Main_info> getAllInfo() {
//        return mainInfoRepository.findAll();
//    }
//
//    @Override
//    public Main_info getInfoById(Long id) {
//        return mainInfoRepository.findById(id).orElse(null); // null тимчасово
//    }


//    private  Main_infoRepository mainInfoRepository;


    @Autowired
    private TableColumnsRepository tableColumnsRep;

    @Autowired
    private InfoTablesRepository infoTablesRep;

    public List<TableColumns> getAllInfo(String tableName) {
        return tableColumnsRep.findByInfoTables_TableName(tableName);
    }

//    public TableColumns getColumn(String tableName, Long columnId) {
//        return tableColumnsRep.findByInfoTables_TableNameAndAndColumnId(tableName, columnId);
//    }

    public TableColumns getColumn(Long id) {
        return tableColumnsRep.findById(id).orElse(null);
    }

    public TableColumns createColumn(String tableName, TableColumns tableColumns){
        InfoTables infoTable = infoTablesRep.findByTableName(tableName);
        if (infoTable != null) {
            tableColumns.setInfoTables(infoTable);
            return tableColumnsRep.save(tableColumns);
        }
        throw new RuntimeException("InfoTable with tableName " + tableName + " not found.");
    }

    public TableColumns updateColumn(Long id, TableColumns updatedTableColumn) {
        TableColumns existingTableColumn = tableColumnsRep.findById(id)
                .orElseThrow(() -> new RuntimeException("TableColumn with id " + id + " not found"));

        existingTableColumn.setJson(updatedTableColumn.getJson());
//        existingTableColumn.setInfoTables(updatedTableColumn.getInfoTables());

        return tableColumnsRep.save(existingTableColumn);
    }


}
