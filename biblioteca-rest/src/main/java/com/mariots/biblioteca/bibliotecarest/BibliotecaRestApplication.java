package com.mariots.biblioteca.bibliotecarest;

import com.mariots.biblioteca.bibliotecarest.api.appinfo.PerfilActivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@EnableJpaRepositories
@SpringBootApplication
public class BibliotecaRestApplication {

	@Autowired
	PerfilActivo perfilActivo;
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaRestApplication.class, args);

	}
	@PostConstruct
	public void mostrarInformacionAplicacion(){
		System.out.println("Inicio de la aplicación");
		perfilActivo.imprimirPerfilActivo();
	}

}
