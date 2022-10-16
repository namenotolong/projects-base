package com.huyong.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.huyong.bo.ConnTree;
import com.huyong.bo.ExecuteSqlBody;
import com.huyong.bo.QueryResult;
import com.huyong.dao.entity.DataConnection;
import com.huyong.dao.mapper.DataConnectionMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        ConnTree connTree = new ConnTree();
        connTree.setConnId(1L).setConnName("test").setName("testDb").setLeaf(false).setDatabase("testDb");

        ConnTree table = new ConnTree();
        table.setDatabase("testDb").setConnId(1L).setLeaf(true).setName("testTable").setConnName("test");

        connTree.setTables(Lists.newArrayList(table));
        return Lists.newArrayList(connTree);
    }

    public QueryResult runSql(ExecuteSqlBody executeSqlBody) {
        QueryResult.ResultColumn resultColumn = new QueryResult.ResultColumn();

        resultColumn.setName("test").setLabel("测试");

        JSONObject item = new JSONObject();
        item.put("test", "hello world");

        return new QueryResult()
                .setTableMeta(Lists.newArrayList(resultColumn))
                .setTableData(Lists.newArrayList(item));
    }
}
