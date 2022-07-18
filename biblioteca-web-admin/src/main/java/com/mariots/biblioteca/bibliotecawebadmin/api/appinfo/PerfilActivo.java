package com.mariots.biblioteca.bibliotecawebadmin.api.appinfo;

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
            String textoAImp = Objects.isNull(perfil)?
                    "No existe un perfil activo definido":
                    "Perfil activo: " + perfil;
            System.out.println(textoAImp);
        }

    }
}

