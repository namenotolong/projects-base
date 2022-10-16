package com.huyong.bo;

import lombok.Data;

@Data
public class ExecuteSqlBody {

    private String sql;

    private Long connId;

    private String database;

}
