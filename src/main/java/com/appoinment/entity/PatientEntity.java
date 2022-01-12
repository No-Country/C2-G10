package com.appoinment.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="patient")
@Getter
@Setter
@SQLDelete(sql = "UPDATE patient SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long dni;
    private String email;
    private String password;
    private boolean deleted = Boolean.FALSE; // soft delete SQL

    @ManyToMany(mappedBy = "patients", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointments;

    public void addAppointment(AppointmentEntity appointment) {
        this.appointments.add(appointment);
    }

    public void removeAppointment(AppointmentEntity appointment) {
        this.appointments.remove(appointment);
    }
}
