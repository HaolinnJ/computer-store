CREATE DATABASE store character SET utf8;

CREATE TABLE t_user (
	uid INT AUTO_INCREMENT,
	username VARCHAR(20) NOT NULL UNIQUE,
	password CHAR(32) NOT NULL,
	salt CHAR(36),
	phone VARCHAR(20),
	email VARCHAR(30),
	gender INT,
	avatar VARCHAR(50),
	is_delete INT,
	created_user VARCHAR(20),
	created_time DATETIME,
	modified_user VARCHAR(20),
	modified_time DATETIME,
	PRIMARY KEY (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

