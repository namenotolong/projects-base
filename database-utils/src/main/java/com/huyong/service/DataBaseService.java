package com.huyong.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huyong.bo.ConnTree;
import com.huyong.bo.ExecuteSqlBody;
import com.huyong.bo.QueryResult;
import com.huyong.bo.Table;
import com.huyong.dao.entity.DataConnection;
import com.huyong.dao.mapper.DataConnectionMapper;
import com.huyong.enums.DbType;
import com.huyong.manager.ConnectionProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DataBaseService {

    @Autowired
    private DataConnectionMapper dataConnectionMapper;

    public List<DataConnection> listConns() {
        return dataConnectionMapper.selectList(null);
    }

    public void deleteConn(Long id) {
        dataConnectionMapper.deleteById(id);
    }

    public DataConnection saveConn(DataConnection dataConnection) {
        QueryWrapper<DataConnection> condition = new QueryWrapper<>();
        condition.eq("name", dataConnection.getName());
        List<DataConnection> dataConnections = dataConnectionMapper.selectList(condition);
        if (CollectionUtils.isNotEmpty(dataConnections)) {
            throw new RuntimeException("该名称连接已存在");
        }
        dataConnectionMapper.insert(dataConnection);
        return dataConnection;
    }

    public DataConnection updateConn(DataConnection dataConnection) {
        if (dataConnection.getId() == null) {
            throw new RuntimeException("id参数缺失");
        }
        QueryWrapper<DataConnection> condition = new QueryWrapper<>();
        condition.eq("name", dataConnection.getName());
        condition.ne("id", dataConnection.getId());
        List<DataConnection> dataConnections = dataConnectionMapper.selectList(condition);
        if (CollectionUtils.isNotEmpty(dataConnections)) {
            throw new RuntimeException("该名称连接已存在");
        }
        dataConnectionMapper.updateById(dataConnection);
        return dataConnection;
    }

    public List<ConnTree> listDbs(Long connId) {
        DataConnection dataConnection = dataConnectionMapper.selectById(connId);
        if (dataConnection == null) {
            throw new RuntimeException("连接信息不存在");
        }
        return ConnectionProcessor
                .getConnectionProcessor(dataConnection.getDbType())
                .totalDatabases(dataConnection)
                .stream()
                .map(e -> new ConnTree()
                        .setConnName(dataConnection.getName())
                        .setConnId(dataConnection.getId())
                        .setDatabase(e))
                .collect(Collectors.toList());
    }

    public List<ConnTree> listTables(Long connId, String database) {
        DataConnection dataConnection = dataConnectionMapper.selectById(connId);
        if (dataConnection == null) {
            throw new RuntimeException("连接信息不存在");
        }
        return ConnectionProcessor
                .getConnectionProcessor(dataConnection.getDbType())
                .totalTables(dataConnection, database)
                .stream()
                .map(e -> new ConnTree()
                        .setConnName(dataConnection.getName())
                        .setConnId(dataConnection.getId())
                        .setDatabase(database)
                        .setTable(e))
                .collect(Collectors.toList());
    }

    public QueryResult runSql(ExecuteSqlBody executeSqlBody) {

        if (StringUtils.isEmpty(executeSqlBody.getSql())) {
            throw new RuntimeException("查询sql为空");
        }

        DataConnection dataConnection = dataConnectionMapper.selectById(executeSqlBody.getConnId());
        if (dataConnection == null) {
            throw new RuntimeException("连接信息不存在");
        }


        return ConnectionProcessor
                .getConnectionProcessor(dataConnection.getDbType())
                .executeSql(dataConnection, executeSqlBody.getDatabase(), executeSqlBody.getSql());
    }


    public Set<DbType> listSupportTypes() {
        return ConnectionProcessor.getRegisterTypes();
    }

    public Table tableDetail(Long connId, String database, String tableName) {
        DataConnection dataConnection = dataConnectionMapper.selectById(connId);
        if (dataConnection == null) {
            throw new RuntimeException("连接信息不存在");
        }
        return ConnectionProcessor
                .getConnectionProcessor(dataConnection.getDbType())
                .getTableInfo(dataConnection, database, tableName);
    }
}
