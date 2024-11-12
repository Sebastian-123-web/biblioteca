package com.egg.biblioteca.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.services.AutorServicio;

@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    public AutorServicio autorServicio;

    @RequestMapping("/registrar")
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
        try {
            autorServicio.crearAutor(nombre);
        } catch (MiException e) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, e);
            return "autor_form.html";
        }
        return "index.html";
    }
}
