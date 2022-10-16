package com.huyong.manager;

import com.huyong.dao.entity.DataConnection;
import com.huyong.enums.DbType;

import java.util.List;

public interface IDataConnection {

    List<String> totalDatabases(DataConnection dataConnection);

    List<String> totalTables(DataConnection dataConnection, String database);

}
