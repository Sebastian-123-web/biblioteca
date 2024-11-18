package com.egg.biblioteca.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entity.Autor;
import com.egg.biblioteca.entity.Editorial;
import com.egg.biblioteca.entity.Libro;
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

    @GetMapping("/registrar")
    public String registrar(ModelMap modelMap) {
        List<Autor> autores = autorServicio.listarAutor();
        List<Editorial> editoriales = editorialServicio.listarEditorial();
        modelMap.addAttribute("autores",autores);
        modelMap.addAttribute("editoriales",editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String regitro(Long isbn, @RequestParam String titulo, @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo) {
        try {
            System.out.println(isbn);
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El Libro fue registrado exitosamente");
        } catch (MiException e) {
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("error", e.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelMap){
        List<Libro> libros = libroServicio.listarLibros();
        modelMap.addAttribute("libros",libros);
        return "libro_list.html";
    }
}
