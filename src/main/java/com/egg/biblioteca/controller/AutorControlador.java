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

import com.egg.biblioteca.entity.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.services.AutorServicio;

@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    public AutorServicio autorServicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {
            autorServicio.crearAutor(nombre);
            modelo.put("exito", "Autor registrado exitosamente");
        } catch (MiException e) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("error", e.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelMap){
        List<Autor> autores = autorServicio.listarAutor();
        modelMap.addAttribute("autores",autores);
        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelMap) {
        modelMap.put("autor", autorServicio.getOneAutor(id));
        return "autor_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre,ModelMap modelMap) {
        try {
            autorServicio.modificarAutor(id, nombre);
            return "redirect:../lista";
        } catch (MiException e) {
            modelMap.put("error", e.getMessage());
            return "autor_modificar.html";
        }
    }
}
