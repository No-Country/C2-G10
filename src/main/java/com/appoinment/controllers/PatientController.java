package com.appoinment.controllers;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.dto.PatientDTO;
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

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/registration")
    public String patientForm() {
        return "patient-form";
    }

    // Metodo para el submit del formulario de registro
    @PostMapping("/registration")
    public String savePatient(ModelMap modelMap, @RequestParam String name, @RequestParam Long dni, @RequestParam String email, @RequestParam String password) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName(name);
        patientDTO.setDni(dni);
        patientDTO.setEmail(email);
        patientDTO.setPassword(password);

        try {
            patientService.save(patientDTO);
            modelMap.put("exito", "Registro exitoso");
            // deberia redireccionar al login
            return "redirect:/patients/registration";
        } catch (Exception e) {
            modelMap.put("error", "Fallo el registro");
            return "patient-form";
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll() {
        List<PatientDTO> patientList = this.patientService.getAll();
        return ResponseEntity.ok(patientList);
    }


    /*
        Recibe el id del paciente para obtener su turno asignado
     */
    @GetMapping("/appointment/{id}")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(this.patientService.getAppointment(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable Long id, @RequestBody PatientDTO dto) {
        PatientDTO result = this.patientService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
