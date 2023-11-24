package com.rsreu.rsreu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RsreuApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsreuApplication.class, args);
	}

}
