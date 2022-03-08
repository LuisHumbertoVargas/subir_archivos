package com.subir.archivos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class InicioController {
    
    @GetMapping("/")
    public String page(Model model) {
        log.info("Entrando al index");
        return "index";
    }
    
}
