package com.github.dge1992.ssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  dong
 * @create  2018/1/26 10:12
 * @desc 声明式事务实现读写分离
 **/
@SpringBootApplication
public class DeclarationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeclarationApplication.class, args);
	}

}
