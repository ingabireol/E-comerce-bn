package com.olim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan("com")
//@EnableJpaRepositories("com.olim")
@SpringBootApplication
public class EComerceBnApplication {

	public static void main(String[] args) {
		SpringApplication.run(EComerceBnApplication.class, args);
	}

}
