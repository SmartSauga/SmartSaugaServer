package ca.smartsauga.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SmartSaugaServerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SmartSaugaServerApplication.class, args);
	}

}

/*
@SpringBootApplication
public class SmartSaugaServerApplication{

	public static void main(String[] args) {
		SpringApplication.run(SmartSaugaServerApplication.class, args);
	}

}
*/
