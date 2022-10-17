package com.huyong.manager;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huyong.bo.QueryResult;
import com.huyong.dao.entity.DataConnection;
import com.huyong.enums.DbType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseDataConnection implements IDataConnection {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getJdbcUrl(DataConnection dataConnection, String database) {
        if (StringUtils.isEmpty(database)) {
            return String.format(dataConnection.getDbType().getJdbcFormat(), dataConnection.getHost(), "");
        }
        return String.format(dataConnection.getDbType().getJdbcFormat(), dataConnection.getHost(), database);
    }

    @Override
    public List<String> totalDatabases(DataConnection dataConnection) {
        try {
            DbType dbType = dataConnection.getDbType();
            Class.forName(dbType.getDriver());
            Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection, null), dataConnection.getLoginUser(), dataConnection.getPassword());

            if (null == connection) {
                throw new RuntimeException("获取连接失败");
            }
            ResultSet catalogs = connection.getMetaData().getCatalogs();
            List<String> results = Lists.newArrayList();
            while (catalogs.next()) {
                results.add(catalogs.getString(1));
            }
            return results;
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
            Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection, database), dataConnection.getLoginUser(), dataConnection.getPassword());

            if (null == connection) {
                return null;
            }
            ResultSet tables = null;

            DatabaseMetaData metaData = connection.getMetaData();

            tables = metaData.getTables(database, null, null, new String[]{"TABLE"});
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

    public QueryResult executeSql(DataConnection dataConnection, String database, String sql) {
        try {
            DbType dbType = dataConnection.getDbType();
            Class.forName(dbType.getDriver());
            Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection, database), dataConnection.getLoginUser(), dataConnection.getPassword());

            if (null == connection) {
                throw new RuntimeException("获取连接失败");
            }
            Statement statement = connection.createStatement();
            boolean execute = statement.execute(sql);

            QueryResult queryResult = new QueryResult();
            List<QueryResult.ResultColumn> columns = Lists.newArrayList();
            List<JSONObject> results = Lists.newArrayList();
            if (execute) {

                queryResult.setTableMeta(columns).setTableData(results);
                try (ResultSet resultSet = statement.getResultSet()) {
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    if (resultSetMetaData != null) {
                        int columnCount = resultSetMetaData.getColumnCount();

                        for (int i = 1; i <= columnCount; i++) {
                            QueryResult.ResultColumn resultColumn = new QueryResult.ResultColumn()
                                    .setName(resultSetMetaData.getColumnName(i))
                                    .setType(resultSetMetaData.getColumnTypeName(i))
                                    .setLabel(resultSetMetaData.getColumnLabel(i));
                            columns.add(resultColumn);
                        }
                        // 迭代输出ResultSet对象
                        while (resultSet.next()) {
                            // 依次输出每列的值
                            JSONObject jsonObject = new JSONObject();
                            for (int i = 0 ; i < columnCount ; i++ ) {
                                jsonObject.put(resultSetMetaData.getColumnLabel(i + 1), resultSet.getString(i + 1));
                            }
                            results.add(jsonObject);
                        }
                    }
                }
            } else {
                logger.warn("execute result empty:{[]}", sql);
            }
            return queryResult;
        } catch (Exception e) {
            logger.error("get connection error", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public DbType supportType() {
        return DbType.MYSQL;
    }
}
