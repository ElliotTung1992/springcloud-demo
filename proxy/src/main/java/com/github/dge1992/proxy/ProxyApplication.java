package com.github.dge1992.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	/**
	 * 如果加入容器的目标对象有实现接口,用JDK代理
	 * 如果目标对象没有实现接口,用Cglib代理
	 */
}
