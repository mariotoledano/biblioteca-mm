package com.mariots.biblioteca.bibliotecawebadmin.repository;

import com.mariots.biblioteca.bibliotecawebadmin.api.feign.FeignConfig;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Autor;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.nuevorecurso.AutorNuevo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "biblioteca-rest", url ="localhost:8085", configuration = FeignConfig.class)
public interface BibliotecaRestProxy {


    @PostMapping(value = "/autores")
    public ResponseEntity<Autor> registrarNuevoAutor(@RequestBody AutorNuevo autor);

}
