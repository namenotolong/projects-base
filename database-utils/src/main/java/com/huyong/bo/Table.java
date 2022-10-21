package com.huyong.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Table {

    /**
     * 表名称
     */
    private String name;
    /**
     * 注释
     */
    private String remarks;
    /**
     * 主键字段
     */
    private String primaryKey;

    private List<Index> indexList;
    private List<Column> columnList;
}
