package com.ldubgd.info.controllers;

//import com.ldubgd.info.models.Main_info;
//import com.ldubgd.info.service.Main_infoService;
//import com.ldubgd.info.service.Main_infoServiceInterface;

import com.ldubgd.info.models.InfoTables;
import com.ldubgd.info.models.SystemUsers;
import com.ldubgd.info.models.TableColumns;
import com.ldubgd.info.service.InfoTableService;
import com.ldubgd.info.service.Main_infoService;
import com.ldubgd.info.service.SystemUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin
public class Main_infoController {

    @Autowired
    private SystemUsersService systemUsersService;

    @Autowired
    private InfoTableService infoTableService;

    @Autowired
    private Main_infoService main_infoService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/getTableColumns")
    public List<TableColumns> list(@RequestParam(name = "tableName"/*, defaultValue = "LDUBGD"*/) String tableName){
        System.out.println(main_infoService.getAllInfo(tableName));
        return main_infoService.getAllInfo(tableName);
    }

//    @GetMapping("/getTableColumn")
//    public TableColumns getTableColumn(@RequestParam(name = "tableName", defaultValue = "LDUBGD") String tableName,
//                                             @RequestParam(name = "id", defaultValue = "1") Long id
//    ){
//        System.out.println(main_infoService.getColumn(tableName, id));
//        return main_infoService.getColumn(tableName, id);
//    }

    @GetMapping("/getTableColumn")
    public TableColumns getTableColumn(@RequestParam(name = "id"/*, defaultValue = "1"*/) Long id){
        System.out.println(main_infoService.getColumn(id));
        return main_infoService.getColumn(id);
    }

    @PostMapping("/createTableColumn")
    public String createTableColumn(@RequestParam(name = "tableName"/*, defaultValue = "LDUBGD"*/) String tableName,
                                    @RequestBody TableColumns tableColumns){
        main_infoService.createColumn(tableName, tableColumns);

        return "Інформацію додано до таблиці";
    }

    @PutMapping("/updateTableColumn") /*TODO НЕ ПРАЦЮЄ ЯК ТРЕБА...*/
    public ResponseEntity<TableColumns> /*String*/ updateTableColumn(@RequestParam(name = "id"/*, defaultValue = "LDUBGD"*/) Long id,
                                    @RequestBody TableColumns tableColumns){
//        main_infoService.updateColumn(id, tableColumns);
//        return "інофрмацію в колонці змінено";

        TableColumns updatedColumn = main_infoService.updateColumn(id, tableColumns);
        return new ResponseEntity<>(updatedColumn, HttpStatus.OK);
    }


    @PostMapping("/createTable")
    public String createTable( @RequestBody InfoTables infoTables){
        infoTableService.createTable(infoTables);

        return "Таблицю створено";
    }

    @GetMapping("/getTableParam")
    public InfoTables getTableParam (@RequestParam(name = "tableName"/*, defaultValue = "Lviv"*/) String tableName) {
        System.out.println(infoTableService.getAllTableInfoByTableName(tableName));
        return infoTableService.getAllTableInfoByTableName(tableName);
    }


    @GetMapping("/getTableParamById")
    public InfoTables getTableParamById (@RequestParam(name = "id") Long id) {
        System.out.println(infoTableService.getAllTableInfoById(id));
        return infoTableService.getAllTableInfoById(id);
    }

    @GetMapping("/getTableNameBySys_manager_id")
    public List<String> getAllTableNameBySys_manager_id (@RequestParam(name = "id") Long id) {
        System.out.println(infoTableService.getAllTableNameBySys_manager_id(id));
        return infoTableService.getAllTableNameBySys_manager_id(id);
    }







    @GetMapping("/getAllUsers")
    public List<SystemUsers> getAllUsers(@RequestParam(name = "id", defaultValue = "1") Long id) {
        System.out.println(systemUsersService.getAllUsers(id));
        return systemUsersService.getAllUsers(id);
    }


    @GetMapping("/getUser")
    public SystemUsers getUser(@RequestParam(name = "m_id", defaultValue = "1") Long m_id,
                               @RequestParam(name = "u_id", defaultValue = "1") Long u_id) {
        System.out.println(systemUsersService.getUser(m_id, u_id));
        return systemUsersService.getUser(m_id, u_id);
    }




    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }




//--     SELECT *
//            -- 	FROM system_managers
//-- 		JOIN info_tables ON info_tables.system_managers_id = system_managers.id
//-- 			JOIN table_columns ON table_columns.info_tables_id = info_tables.id
//
//
//    select * from info_tables
//
//-- select * from system_managers
//
//
//-- SELECT table_name from info_tables WHERE info_tables.system_managers_id = 1



//    @Autowired
//    private Main_infoService mainInfoService;
//
//
//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Hello, World!";
//    }
//
//
//    @GetMapping("/getAll")
//    public List<Main_info> list(){
//        System.out.println(mainInfoService.getAllInfo());
//        return mainInfoService.getAllInfo();
//    }
//
//
//    @GetMapping("/getById/{id}")
//    public Main_info getByID(@PathVariable Long id) {
//        return mainInfoService.getInfoById(id);
//    }
//
//    @PostMapping("/add")
//    public String add( @RequestBody Main_info mainInfo){
//        mainInfoService.saveInfo(mainInfo);
//
//        return "Інформацію додано";
//    }

//    private Main_infoService mainInfoService;







}
