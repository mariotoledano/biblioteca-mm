package com.mariots.biblioteca.bibliotecalauncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaLauncherApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaLauncherApplication.class, args);
		System.out.println("Incio biblioteca-launch");
	}

}
