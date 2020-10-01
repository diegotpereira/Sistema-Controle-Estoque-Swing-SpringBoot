package br.com.java;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.java.view.ListaMercadoriasFrame;



@SpringBootApplication
public class SistemaControleEstoqueSwingSpringBootApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SistemaControleEstoqueSwingSpringBootApplication.class, args);
		new ListaMercadoriasFrame();
	}

}
