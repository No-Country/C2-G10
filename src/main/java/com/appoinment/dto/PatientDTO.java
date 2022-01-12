package com.appoinment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PatientDTO {

    private Long id;
    private String name;
    private Long dni;
    private String email;
    private String password;
    private List<AppointmentDTO> appointmentDTO;
}
