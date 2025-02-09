package br.com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendOrderApplication.class, args);
		System.out.println("Rodando Servidor PEDIDO");
	}

}
