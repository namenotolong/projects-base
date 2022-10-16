package com.huyong.manager;

import com.huyong.dao.entity.DataConnection;
import com.huyong.enums.DbType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDataConnection extends BaseDataConnection{

    public static void main(String[] args) {
        DataConnection dataConnection = new DataConnection();
        dataConnection.setLoginUser("root");
        dataConnection.setPassword("123456");
        dataConnection.setHost("master:3306");
        dataConnection.setDatabase("sys");
        dataConnection.setDbType(DbType.MYSQL);
        System.out.println(new MySQLDataConnection().totalDatabases(dataConnection));
    }

    @Override
    public List<String> totalDatabases(DataConnection dataConnection) {
        try {
            DbType dbType = dataConnection.getDbType();
            Class.forName(dbType.getDriver());
            Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection), dataConnection.getLoginUser(), dataConnection.getPassword());

            if (null == connection) {
                return null;
            }
            ResultSet tables = null;

            DatabaseMetaData metaData = connection.getMetaData();
            String schema = null;
            try {
                schema = metaData.getConnection().getSchema();
            } catch (SQLException e) {
                logger.error("cant not get the schema : {}", e.getMessage(), e);
            }

            tables = metaData.getTables(dataConnection.getDatabase(), null, null, new String[]{"TABLE"});
            if (null == tables) {
                return null;
            }

            List<String> tableList = new ArrayList<>();
            while (tables.next()) {
                String name = tables.getString("TABLE_NAME");
                tableList.add(name);
            }
            return tableList;
        } catch (Exception e) {
            logger.error("get connection error", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> totalTables(DataConnection dataConnection, String database) {
        try {
            DbType dbType = dataConnection.getDbType();
            Class.forName(dbType.getDriver());
            Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection), dataConnection.getLoginUser(), dataConnection.getPassword());

            if (null == connection) {
                return null;
            }
            ResultSet tables = null;

            DatabaseMetaData metaData = connection.getMetaData();
            String schema = null;
            try {
                schema = metaData.getConnection().getSchema();
            } catch (SQLException e) {
                logger.error("cant not get the schema : {}", e.getMessage(), e);
            }

            tables = metaData.getTables(dataConnection.getDatabase(), null, "%", new String[]{"TABLE"});
            if (null == tables) {
                return null;
            }

            List<String> tableList = new ArrayList<>();
            while (tables.next()) {
                String name = tables.getString("TABLE_NAME");
                tableList.add(name);
            }
            return tableList;
        } catch (Exception e) {
            logger.error("get connection error", e);
            throw new RuntimeException(e);
        }
    }
}
