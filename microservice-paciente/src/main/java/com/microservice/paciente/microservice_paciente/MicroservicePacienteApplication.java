package com.microservice.paciente.microservice_paciente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicePacienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePacienteApplication.class, args);
	}

}
