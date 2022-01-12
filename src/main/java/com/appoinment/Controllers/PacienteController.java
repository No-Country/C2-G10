package com.appoinment.Controllers;

import com.appoinment.Entidades.Paciente;
import com.appoinment.InterfaceService.iPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class PacienteController {

    @Autowired
    private iPacienteService service;

    //LISTAR USUARIOS DEL MYSQL
    @GetMapping("/listar")
    public String listar(Model model){
        List<Paciente> pacientes = service.listar();
        model.addAttribute("Pacientes", pacientes);
        return "index.html";
    }

    @GetMapping("/nuevo")
    public String agregar(Model model){
        model.addAttribute("Paciente",new Paciente());
        return "form.html";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated Paciente p, Model model) {
        service.guardar(p);
        return "redirect:/listar";
    }



    @GetMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id){
        service.eliminar(id);
        return "redirect:/listar";
    }
}
