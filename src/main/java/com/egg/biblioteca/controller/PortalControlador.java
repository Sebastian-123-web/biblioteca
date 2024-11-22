package com.egg.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @PostMapping("/registro")
    public String registro() {
        return "index.html";
    }
}
