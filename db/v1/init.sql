SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS sys_user;


/* Create Tables */

CREATE TABLE sys_user
(
	id bigint(20) auto_increment COMMENT '编号',
	login_name varchar(100) NOT NULL unique COMMENT '登录名',
	password varchar(100) NOT NULL COMMENT '密码',
	name varchar(100) NOT NULL COMMENT '姓名',
	email varchar(200) COMMENT '邮箱',
	phone varchar(200) COMMENT '电话',
	user_tag bigint(20) COMMENT '用户标签',
	avatar varchar(255) COMMENT '用户头像',
	login_ip varchar(100) COMMENT '最后登陆IP',
	login_date datetime COMMENT '最后登陆时间',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_date datetime NOT NULL COMMENT '更新时间',
	del_flag tinyint(1) DEFAULT 0 NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '用户表';

CREATE INDEX sys_user_update_date ON sys_user (update_date ASC);
CREATE INDEX sys_user_del_flag ON sys_user (del_flag ASC);