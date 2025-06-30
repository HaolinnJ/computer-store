CREATE TABLE t_order (
	oid INT AUTO_INCREMENT,
	uid INT NOT NULL,
	recv_name VARCHAR(20) NOT NULL,
	recv_phone VARCHAR(20) ,
	recv_province VARCHAR(15) ,
	recv_city VARCHAR(15) ,
	recv_area VARCHAR(15) ,
	recv_address VARCHAR(50) ,
	total_price BIGINT ,
	status INT ,
	order_time DATETIME ,
	pay_time DATETIME ,
	created_user VARCHAR(20) ,
	created_time DATETIME ,
	modified_user VARCHAR(20) ,
	modified_time DATETIME ,
	PRIMARY KEY (oid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_order_item (
	id INT AUTO_INCREMENT ,
	oid INT NOT NULL ,
	pid INT NOT NULL ,
	title VARCHAR(100) NOT NULL ,
	image VARCHAR(500) ,
	price BIGINT ,
	num INT ,
	created_user VARCHAR(20) ,
	created_time DATETIME ,
	modified_user VARCHAR(20) ,
	modified_time DATETIME ,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;