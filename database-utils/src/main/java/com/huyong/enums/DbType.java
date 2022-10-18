package com.huyong.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum DbType {
    /**
     *
     */

    MYSQL(1, "MySQL", "com.mysql.jdbc.Driver", "jdbc:mysql://%s/%s"),
    POSTGRESQL(2, "PostgresSQL", "org.postgresql.Driver", "jdbc:postgresql://%s/%s"),
    //ORACLE(2, "Oracle"),
    //POSTGRESQL(3, "PostgresSQL")
    ;

    @EnumValue
    private final Integer id;
    private final String name;

    private final String driver;

    private final String jdbcFormat;

    DbType(Integer id, String name, String driver, String jdbcFormat) {
        this.id = id;
        this.name = name;
        this.driver = driver;
        this.jdbcFormat = jdbcFormat;
    }

}
