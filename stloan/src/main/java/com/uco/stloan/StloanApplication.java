package com.uco.stloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.uco.stloan")
@EnableJpaRepositories("com.uco.stloan")
//@SpringBootApplication(scanBasePackages = "com.uco.stloan")
public class StloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(StloanApplication.class, args);
	}

}
