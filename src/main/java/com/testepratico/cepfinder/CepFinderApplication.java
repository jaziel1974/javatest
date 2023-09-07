package com.testepratico.cepfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CepFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepFinderApplication.class, args);
	}
}
