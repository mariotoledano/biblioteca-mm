package com.mariots.biblioteca.bibliotecarest.api.appinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class PerfilActivo {

    @Autowired
    private Environment environment;
    public void imprimirPerfilActivo(){
        for(String perfil:environment.getActiveProfiles()){
            String infoPerfil = Objects.isNull(perfil)?
                    "No existe un perfil activo definido para el inicio de la aplicación":
                    "La aplicación ha iniciado con el perfil: " + perfil;
            System.out.println(infoPerfil);
        }

    }
}

