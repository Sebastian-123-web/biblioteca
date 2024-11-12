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
import com.egg.biblioteca.services.EditorialServicio;
import com.egg.biblioteca.services.LibroServicio;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @RequestMapping("/registrar")
    public String registrar() {
        return "libro_form.html";
    }

    @PostMapping("/regitro")
    public String regitro(@RequestParam(required = false) Long isbn, @RequestParam String titulo, @RequestParam(required = false) Integer ejemplar, @RequestParam String idAutor, @RequestParam String idEditorial) {
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplar, idAutor, idEditorial);
        } catch (MiException e) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, e);
            return "libro_form.html";
        }
        return "index.html";
    }
}
