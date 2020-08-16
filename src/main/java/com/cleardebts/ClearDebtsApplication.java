package com.cleardebts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.cleardebts" })
public class ClearDebtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearDebtsApplication.class, args);
	}

}
