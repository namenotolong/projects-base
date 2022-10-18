DROP TABLE IF EXISTS data_connection;
CREATE TABLE data_connection
(
    id BIGINT(20) NOT NULL auto_increment COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '名称',
    db_type INT(11) NULL DEFAULT NULL COMMENT '数据库类型',
    host VARCHAR(50) NULL DEFAULT NULL COMMENT '主机',
    login_user VARCHAR(50) NULL DEFAULT NULL COMMENT '用户',
    password VARCHAR(50) NULL DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (id)
);
