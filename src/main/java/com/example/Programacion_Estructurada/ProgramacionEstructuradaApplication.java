package com.example.Programacion_Estructurada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProgramacionEstructuradaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgramacionEstructuradaApplication.class, args);
    }
}