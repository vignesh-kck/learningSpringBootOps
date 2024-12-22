package com.annztech.rightcareApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RightcareApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RightcareApiApplication.class, args);
	}

}
