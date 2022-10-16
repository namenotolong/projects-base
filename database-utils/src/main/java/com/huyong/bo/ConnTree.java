package com.huyong.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ConnTree {

    private String name;

    private Long connId;

    private Boolean leaf;

    private String database;

    private String connName;

    private List<ConnTree> tables;

}
