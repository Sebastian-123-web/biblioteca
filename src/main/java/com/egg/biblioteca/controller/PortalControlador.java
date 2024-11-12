package com.egg.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
