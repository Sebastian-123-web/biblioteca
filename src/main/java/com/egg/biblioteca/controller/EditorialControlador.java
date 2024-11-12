package com.egg.biblioteca.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.services.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @RequestMapping("registrar")
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
        try {
            editorialServicio.crearEditorial(nombre);
        } catch (MiException e) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, e);
            return "editorial_form.html";
        }
        return "index.html";
    }
}
