package com.huyong.manager;

import com.huyong.bo.QueryResult;
import com.huyong.bo.Table;
import com.huyong.dao.entity.DataConnection;
import com.huyong.enums.DbType;

import java.util.List;

public interface IDataConnection {

    List<String> totalDatabases(DataConnection dataConnection);

    List<String> totalTables(DataConnection dataConnection, String database);

    QueryResult executeSql(DataConnection dataConnection, String database, String sql);

    DbType supportType();

    Table getTableInfo(DataConnection dataConnection, String database, String tableName);

}
