package com.foxgiker.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
