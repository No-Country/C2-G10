package com.appoinment.controllers;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAll() {
        List<AppointmentDTO> dtoList = this.appointmentService.getAllAppointments();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointment) {
        AppointmentDTO result = this.appointmentService.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
