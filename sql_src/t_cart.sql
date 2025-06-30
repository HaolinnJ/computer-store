CREATE TABLE t_cart (
	cid INT AUTO_INCREMENT COMMENT 'Cart data id',
	uid INT NOT NULL COMMENT 'User id',
	pid INT NOT NULL COMMENT 'Product id',
	price BIGINT COMMENT 'price',
	num INT COMMENT 'product num',
	created_user VARCHAR(20),
	created_time DATETIME,
	modified_user VARCHAR(20),
	modified_time DATETIME,
	PRIMARY KEY (cid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;