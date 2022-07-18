package com.mariots.biblioteca.bibliotecawebadmin.api.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public CustomErrorDecoder customDecoder() {
        return new CustomErrorDecoder();
    }
}
