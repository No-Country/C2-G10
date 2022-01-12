package com.appoinment.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "appointment_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate appointmentDate; // fecha del turno

    @OneToOne
    @JoinColumn(name = "id_patient", nullable = false)
    private PatientEntity patient;

    // Agregar especializacion medica: Cardiologia, Dermatologia, Pediatria etc...
}
