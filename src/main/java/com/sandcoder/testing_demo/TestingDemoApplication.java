package com.sandcoder.testing_demo;

import com.sandcoder.testing_demo.feign.PostFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication @EnableFeignClients(basePackages = "com.sandcoder.testing_demo.feign")
public class TestingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingDemoApplication.class, args);
	}

}
