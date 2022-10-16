DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);


DELETE FROM t_user;

INSERT INTO t_user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');

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
