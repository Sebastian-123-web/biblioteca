package com.egg.biblioteca.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entity.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.services.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("registrar")
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito", "Editorial registrado exitosamente");
        } catch (MiException e) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("error", e.getMessage());
            return "editorial_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelMap){
        List<Editorial> editoriales = editorialServicio.listarEditorial();
        modelMap.addAttribute("editoriales",editoriales);
        return "editorial_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelMap) {
        modelMap.put("editorial", editorialServicio.getOnEditorial(id));
        return "editorial_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelMap) {
        try {
            editorialServicio.modificarEditorial(id, nombre);
            return "redirect:../lista";
        } catch (MiException e) {
            modelMap.put("error", e.getMessage());
            return "editorial_modificar.html";
        }
    }
}
