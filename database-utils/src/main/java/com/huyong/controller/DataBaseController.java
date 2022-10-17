package com.huyong.controller;

import com.huyong.bo.ConnTree;
import com.huyong.bo.ExecuteSqlBody;
import com.huyong.bo.QueryResult;
import com.huyong.dao.entity.DataConnection;
import com.huyong.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/database")
public class DataBaseController {

    @Autowired
    private DataBaseService dataBaseService;

    @GetMapping("list-conns")
    public List<DataConnection> listConns() {
        return dataBaseService.listConns();
    }

    @DeleteMapping("delete-conn/{id}")
    public void deleteConn(@PathVariable Long id) {
        dataBaseService.deleteConn(id);
    }

    @PostMapping("save-conn")
    public DataConnection saveConn(@RequestBody DataConnection dataConnection) {
        return dataBaseService.saveConn(dataConnection);
    }

    @PostMapping("update-conn")
    public DataConnection updateConn(@RequestBody DataConnection dataConnection) {
        return dataBaseService.updateConn(dataConnection);
    }

    @GetMapping("list-dbs/{connId}")
    public List<ConnTree> listDbs(@PathVariable Long connId) {
        return dataBaseService.listDbs(connId);
    }

    @GetMapping("list-tables/{connId}/{database}")
    public List<ConnTree> listTables(@PathVariable Long connId, @PathVariable String database) {
        return dataBaseService.listTables(connId, database);
    }

    @PostMapping("run-sql")
    public QueryResult runSql(@RequestBody ExecuteSqlBody executeSqlBody) {
        return dataBaseService.runSql(executeSqlBody);
    }

}
