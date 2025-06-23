package com.cy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//MapperScan 项目启动时加载所有接口文件
@MapperScan("com.cy.store.mapper")

public class StoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(StoreApplication.class, args);
	}

}
