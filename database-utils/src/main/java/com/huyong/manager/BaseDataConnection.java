package com.huyong.manager;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import com.huyong.bo.Column;
import com.huyong.bo.Index;
import com.huyong.bo.QueryResult;
import com.huyong.bo.Table;
import com.huyong.dao.entity.DataConnection;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    private static final String SCHEMA = "";

    static final String[] TABLE_TYPES = {"TABLE"};

    protected List<Index> getIndex(DatabaseMetaData metaData, String tableName) {
        List<Index> list = new ArrayList<>();
        if (metaData == null) {
            return list;
        }
        Map<String, List<String>> indexMap = new HashMap<>(16);
        Map<String, Index> map = new HashMap<>(16);
        try {
            ResultSet indexInfo = metaData.getIndexInfo(null, SCHEMA, tableName, false, false);
            while (indexInfo.next()) {
                String indexName = indexInfo.getString("INDEX_NAME");
                String columnName = indexInfo.getString("COLUMN_NAME");
                boolean unique = !indexInfo.getBoolean("NON_UNIQUE");
                short ordinalPosition = indexInfo.getShort("ORDINAL_POSITION");
                if (!map.containsKey(indexName)) {
                    map.put(indexName, new Index().setName(indexName).setUnique(unique));
                }
                // 暂存索引列
                List<String> indexList = indexMap.computeIfAbsent(indexName, k -> new ArrayList<>());
                indexList.add(ordinalPosition - 1, columnName);
            }

            map.forEach((indexName, index) -> {
                List<String> indexList = indexMap.get(indexName);
                index = index.setFields(String.join(",", indexList));
                list.add(index);
            });
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    protected List<Column> getColumn(DatabaseMetaData metaData, String tableName) {
        List<Column> list = new ArrayList<>();
        if (metaData == null) {
            return list;
        }
        try {
            ResultSet columns = metaData.getColumns(null, "%", tableName, "%");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                int dataSize = columns.getInt("COLUMN_SIZE");
                int digits = columns.getInt("DECIMAL_DIGITS");
                boolean nullable = columns.getBoolean("NULLABLE");
                boolean autoIncrement = columns.getBoolean("IS_AUTOINCREMENT");
                String defaultValue = columns.getString("COLUMN_DEF");
                if (defaultValue == null) {
                    defaultValue = "";
                }
                String remarks = columns.getString("REMARKS");

                list.add(new Column(columnName, columnType, dataSize, digits, nullable, autoIncrement, defaultValue, remarks));
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    @Override
    public Table getTableInfo(DataConnection dataConnection, String database, String tableName) {
        try (Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection, database), dataConnection.getLoginUser(), dataConnection.getPassword())) {
            if (null == connection) {
                throw new RuntimeException("获取连接失败");
            }
            DatabaseMetaData metaData = connection.getMetaData();
            if (metaData == null) {
                throw new RuntimeException("获取表信息失败");
            }
            List<Index> index = getIndex(metaData, tableName);
            List<Column> column = getColumn(metaData, tableName);
            return new Table().setName(tableName).setIndexList(index).setColumnList(column);
        } catch (Exception e) {
            logger.error("get table info error", e);
            throw new RuntimeException("获取表信息失败");
        }
    }

    @Override
    public List<String> totalDatabases(DataConnection dataConnection) {
        try (Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection, null), dataConnection.getLoginUser(), dataConnection.getPassword())) {
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
        try (Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection, database), dataConnection.getLoginUser(), dataConnection.getPassword())) {
            if (null == connection) {
                return null;
            }
            ResultSet tables = null;

            DatabaseMetaData metaData = connection.getMetaData();

            tables = metaData.getTables(database, null, null, TABLE_TYPES);
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

    protected String preProcessSql(String sql) {
        sql = sql.trim();
        if (sql.contains("limit") || sql.contains("LIMIT")) {
            return sql;
        }
        if (sql.endsWith(";")) {
            sql = sql.replaceAll(";", "");
        }
        return sql + " \nlimit 100";
    }

    public QueryResult executeSql(DataConnection dataConnection, String database, String sql) {
        QueryResult queryResult = new QueryResult();
        try (Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection, database), dataConnection.getLoginUser(), dataConnection.getPassword())) {
            if (null == connection) {
                throw new RuntimeException("获取连接失败");
            }
            Statement statement = connection.createStatement();
            sql = preProcessSql(sql);
            boolean execute = statement.execute(sql);

            queryResult.setExecuteSql(sql);
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
            return queryResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("execute sql:[{}] error", sql, e);
            return queryResult.setSuccess(false).setErrorMsg(e.getMessage());
        }
    }

}
