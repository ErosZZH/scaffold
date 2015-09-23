SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS sys_user;


/* Create Tables */

CREATE TABLE sys_user
(
	id bigint(20) auto_increment,
	login_name varchar(100) NOT NULL unique,
	password varchar(100) NOT NULL,
	name varchar(100) NOT NULL,
	email varchar(200),
	phone varchar(200),
	user_tag bigint(20),
	avatar varchar(255),
	login_ip varchar(100),
	login_date datetime,
	create_date datetime NOT NULL,
	update_date datetime NOT NULL,
	del_flag tinyint(1) DEFAULT 0 NOT NULL,
	company_id bigint(20) not null,
	PRIMARY KEY (id)
);

CREATE INDEX sys_user_update_date ON sys_user (update_date ASC);
CREATE INDEX sys_user_del_flag ON sys_user (del_flag ASC);

drop table if exists sys_company;
create table sys_company
(
	id bigint(20) auto_increment,
	name varchar(100) NOT NULL,
	phone varchar(100) NOT NULL,
	primary key (id)
);

drop table if exists sys_photo;
create table sys_photo
(
	id bigint(20) auto_increment,
	url varchar(100) NOT NULL,
	size int(11) NOT NULL,
	user_id bigint(20),
	primary key (id),
	FOREIGN KEY (user_id) REFERENCES sys_user(id)
);
