package com.huyong.manager;

import com.huyong.bo.QueryResult;
import com.huyong.dao.entity.DataConnection;
import com.huyong.enums.DbType;
import org.springframework.stereotype.Component;

@Component
public class MySQLDataConnection extends BaseDataConnection{

    public static void main(String[] args) {
        DataConnection dataConnection = new DataConnection();
        dataConnection.setLoginUser("root");
        dataConnection.setPassword("123456");
        dataConnection.setHost("master:3306");
        dataConnection.setDbType(DbType.MYSQL);
        QueryResult test = new MySQLDataConnection().executeSql(dataConnection, "test", "select date(now()) as nowtime");
        System.out.println(test);
    }
}
