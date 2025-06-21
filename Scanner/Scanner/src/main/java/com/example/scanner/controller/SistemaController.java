package com.example.scanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SistemaController {

    @GetMapping("/sistema")
    public String index() {
        return "scanner/sistema";
    }

}
