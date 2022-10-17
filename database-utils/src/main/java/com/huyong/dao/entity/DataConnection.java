package com.huyong.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huyong.enums.DbType;
import lombok.Data;

@Data
@TableName("data_connection")
public class DataConnection {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private DbType dbType;

    private String host;

    private String loginUser;

    private String password;
}
