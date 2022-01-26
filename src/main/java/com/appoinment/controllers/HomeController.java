package com.appoinment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) {
        if (error != null) {
            model.put("error", "Email o contraseña incorrectos.");
        }
        return "login.html";
    }

    @GetMapping("/inicio")
    public String inicio(ModelMap model) {
        model.put("title", "Bienvenido a Appointment");
        model.put("description", "Tu panel de autogestion");
        return "patient-panel.html";
    }
}
