package com.wyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.wyj.dao")  
public class WyjSpringbootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(WyjSpringbootSecurityApplication.class, args);
	}
}
