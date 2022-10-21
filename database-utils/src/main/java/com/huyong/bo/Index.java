package com.huyong.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Index {

    /**
     * 索引名称
     */
    private String name;
    /**
     * 是否唯一索引
     */
    private boolean unique;
    /**
     * 索引列 逗号分隔
     */
    private String fields;
}
