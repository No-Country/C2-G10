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

    @Column(nullable = false, unique = true)
    private Long dni;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private boolean deleted = Boolean.FALSE; // soft delete SQL

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private AppointmentEntity appointment;
}
