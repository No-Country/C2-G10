package com.appoinment.controllers;

import com.appoinment.entity.PatientEntity;
import com.appoinment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Metodo para el submit del formulario de registro
    @PostMapping("/save")
    public String savePatient(ModelMap model, @RequestParam String name,
                              @RequestParam Long dni, @RequestParam String email, @RequestParam String password) {
        try {
            patientService.save(name, dni, email, password);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "login.html";
        }
        model.put("description", "Hola "+ name + ", tu usuario fue registrado de manera satisfactoria");
        return "patient-panel.html";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, ModelMap model) {
        model.put("patient", this.patientService.getById(id));
        return null; // TODO: vista del formulario para modificar datos
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestParam String name,
                         @RequestParam Long dni, @RequestParam String email,
                         @RequestParam String password, ModelMap model) {
        try {
            this.patientService.update(id, name, dni, email, password);
            model.put("exito", "Modificacion Exitosa.");
            return "patient-panel.html";
        } catch (Exception e) {
            model.put("error", "No se pudó realizar la modificación.");
            return null; // TODO: vista del formulario para modificar datos
        }
    }

    @GetMapping("/list")
    public String getAll(ModelMap model) {
        List<PatientEntity> patientList = this.patientService.getAll();
        model.addAttribute("patients", patientList);
        return null; // TODO: vista que contendra la lista de pacientes
    }


    /*
        Recibe el id del paciente para obtener su turno asignado
     */
    @GetMapping("/appointment/{id}")
    public String getAppointment(@PathVariable Long id, ModelMap model) {
        model.put("appointment", patientService.getAppointment(id));
        return null; // TODO: Retornar el html que contenga la vista de turnos
    }

    /*
        TODO: Ver como implementar
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
