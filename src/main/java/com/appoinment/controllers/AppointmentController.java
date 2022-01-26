package com.appoinment.controllers;

import com.appoinment.entity.AppointmentEntity;
import com.appoinment.errors.ErrorService;
import com.appoinment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/list")
    public String getAll(ModelMap model) {
        List<AppointmentEntity> appointmentList = this.appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointmentList);
        return null; // TODO vista que muestre todos los turnos: panel doctor o admin
    }

    @PostMapping("/save/{id}")
    public String save(@PathVariable Long idPatient, @RequestParam String appointmentDate, @RequestParam String appointmentTime, ModelMap model) {
        try {
            this.appointmentService.save(idPatient, appointmentDate, appointmentTime);
            model.put("exito", "Turno generado de forma exitosa.");
            return "redirect:/inicio";
        } catch (ErrorService e) {
            model.put("error", e.getMessage());
            return "redirect:/inicio";
        }
    }

    @PostMapping("update/{idAppoinment}/patient/{idPatient}")
    public String update(@PathVariable Long idAppointment, @PathVariable Long idPatient,
                         @RequestParam String appointmentDate, @RequestParam String appointmentTime,
                         ModelMap model) {
        try {
            this.appointmentService.update(idAppointment, idPatient, appointmentDate, appointmentTime);
            model.put("exito", "Turno modificado de forma exitosa.");
            return "redirect:/inicio";
        } catch (ErrorService e) {
            model.put("error", "No se pudó realizar la modificación del turno.");
            return "redirect:/inicio";
        }
    }

    @DeleteMapping("/delete/{idAppointment}/patient/{idPatient}")
    public String delete(@PathVariable Long idPatient, @PathVariable Long idAppointment, ModelMap model) {
        try {
            this.appointmentService.delete(idPatient, idAppointment);
            model.put("exito", "Turno eliminado de forma exitosa.");
            return "redirect:/inicio";
        } catch (ErrorService e) {
            model.put("error", "No se ha podido eliminar el turno: " + e.getMessage());
            return "redirect:/inicio";
        }
    }
}
