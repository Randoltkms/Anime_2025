package com.example.servicio_seguridad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioSeguridadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioSeguridadApplication.class, args);
	}

}
