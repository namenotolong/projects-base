package com.huyong.manager;


import com.google.common.collect.Lists;
import com.huyong.dao.entity.DataConnection;
import com.huyong.enums.DbType;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

@Component
public class PostgresDataConnection extends BaseDataConnection {

    private static final String DATABASE_SQL = "SELECT datname FROM pg_catalog.pg_database";
    @Override
    public DbType supportType() {
        return DbType.POSTGRESQL;
    }

    @Override
    public List<String> totalDatabases(DataConnection dataConnection) {
        try (Connection connection = DriverManager.getConnection(getJdbcUrl(dataConnection, null), dataConnection.getLoginUser(), dataConnection.getPassword())) {
            ResultSet resultSet = connection.createStatement().executeQuery(DATABASE_SQL);
            List<String> result = Lists.newArrayList();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (Exception e) {
            logger.error("get postgres databases error", e);
            throw new RuntimeException(e);
        }
    }
}
