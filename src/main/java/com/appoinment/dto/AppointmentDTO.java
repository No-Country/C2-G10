package com.appoinment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class AppointmentDTO {

    private Long id;
    private String appointmentDate;
    //private String appointmentTime;
    private List<PatientDTO> patientDTO;
}
