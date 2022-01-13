package com.appoinment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {

    private Long id;
    private String appointmentDate;
    private Long idPatient;
}
