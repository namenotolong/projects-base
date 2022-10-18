package com.huyong.manager;

import com.huyong.bo.QueryResult;
import com.huyong.dao.entity.DataConnection;
import com.huyong.enums.DbType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLDataConnection extends BaseDataConnection{

    public static void main(String[] args) {
        DataConnection dataConnection = new DataConnection();
        dataConnection.setLoginUser("postgres");
        dataConnection.setPassword("Guan&*(123");
        dataConnection.setHost("master:5435");
        dataConnection.setDbType(DbType.POSTGRESQL);
        List<String> strings = new PostgresDataConnection().totalDatabases(dataConnection);
        System.out.println(strings);
        QueryResult test = new MySQLDataConnection().executeSql(dataConnection, "escheduler", "SELECT datname FROM pg_database;");
        System.out.println(test);
    }


    @Override
    public DbType supportType() {
        return DbType.MYSQL;
    }
}
