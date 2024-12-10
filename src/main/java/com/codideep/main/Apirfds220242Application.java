package com.codideep.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Apirfds220242Application {
	public static void main(String[] args) {
		SpringApplication.run(Apirfds220242Application.class, args);
	}
}