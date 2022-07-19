package com.mariots.biblioteca.bibliotecawebadmin.api.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mariots.biblioteca.bibliotecawebadmin.api.exceptions.ExceptionDesdeFeign;
import com.mariots.biblioteca.bibliotecawebadmin.api.exceptions.ModeloExceptionFeign;
import com.mariots.biblioteca.bibliotecawebadmin.api.exceptions.RecursoNoEncontradoException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {

                ExceptionDesdeFeign exceptionDesdeFeign = new ExceptionDesdeFeign
                        (null, HttpStatus.valueOf(response.status()));

                ModeloExceptionFeign modeloExcepcion = null;

                try (InputStream bodyRespuestaIS = response.body().asInputStream()) {
                        ObjectMapper mapper = new ObjectMapper();
                        String bodyS = convertStreamToString(bodyRespuestaIS);
                        modeloExcepcion = mapper.readValue(bodyS, ModeloExceptionFeign.class);
                        exceptionDesdeFeign.setModeloExcepcionFeing(modeloExcepcion);
                } catch (IOException e) {
                        return new RecursoNoEncontradoException();
                }

                return exceptionDesdeFeign;
        }

        private static String convertStreamToString(InputStream is) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                        while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        try {
                                is.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                return sb.toString();
        }

}
