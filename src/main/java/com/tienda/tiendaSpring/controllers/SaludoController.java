package com.tienda.tiendaSpring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludo(){
        return "Hola mundo desde Spring Boot con github actions ";
    }

    @GetMapping("/saludo/{nombre}")
    public String saludo(@PathVariable String nombre){
        return "Hola " + nombre + " desde Spring Boot";
    }
}
