package com.huyong.manager;

import com.google.common.collect.Maps;
import com.huyong.enums.DbType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class ConnectionProcessor {

    @Autowired
    private List<IDataConnection> dataConnections;

    @PostConstruct
    public void init() {
        if (CollectionUtils.isNotEmpty(dataConnections)) {
            for (IDataConnection dataConnection : dataConnections) {
                register(dataConnection.supportType(), dataConnection);
            }
        }
    }

    private static Map<DbType, IDataConnection> cache = Maps.newHashMap();

    public static void register(DbType dbType, IDataConnection connection) {
        cache.put(dbType, connection);
    }

    public static IDataConnection getConnectionProcessor(DbType dbType) {
        return new MySQLDataConnection();
    }

}
