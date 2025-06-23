package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
	@Autowired // 自动装配
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}
	/**数据库连接池：
	 * Hikari：管理数据库的连接对象
	 * HikariProxyConnection@310572932 wrapping com.mysql.cj.jdbc.ConnectionImpl@5df2023c
	 */
	@Test
	void getConnection() throws SQLException {
		System.out.println(dataSource.getConnection());
	}
}
