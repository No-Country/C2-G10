package com.appoinment.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    /*
    @Column(name = "appointment_time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime appointmentTime; // hora del turno
    */

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name = "patient_appointment",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<PatientEntity> patients = new ArrayList<>(); // paciente del turno

    // Agregar especializacion medica: Cardiologia, Dermatologia, Pediatria etc...
}
