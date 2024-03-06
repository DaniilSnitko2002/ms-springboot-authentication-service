package com.springboot.msauthenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MsAuthenticationServiceApplication {

	/**
	 * The Entry point of the application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MsAuthenticationServiceApplication.class, args);
	}

}
