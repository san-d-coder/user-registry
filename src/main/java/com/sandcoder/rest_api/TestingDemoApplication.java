package com.sandcoder.rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication @EnableFeignClients(basePackages = "com.sandcoder.rest_api.feign")
public class TestingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingDemoApplication.class, args);
	}

}
