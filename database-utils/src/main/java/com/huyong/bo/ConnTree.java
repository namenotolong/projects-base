package com.huyong.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConnTree {

    private String table;

    private Long connId;

    private String database;

    private String connName;
}
