package com.startlion.startlionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan("com.startlion.startlionserver.domain")

@SpringBootApplication
public class StartlionserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartlionserverApplication.class, args);
	}

}
