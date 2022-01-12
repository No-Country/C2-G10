package com.appoinment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {

    private Long id;
    private String name;
    private Long dni;
    private String email;
    private String password;
    private AppointmentDTO appointment;
}
