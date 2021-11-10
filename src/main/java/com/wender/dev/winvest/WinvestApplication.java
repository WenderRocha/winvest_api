package com.wender.dev.winvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WinvestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinvestApplication.class, args);
	}

}
