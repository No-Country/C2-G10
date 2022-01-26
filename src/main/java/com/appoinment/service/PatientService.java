package com.appoinment.service;

import com.appoinment.entity.AppointmentEntity;
import com.appoinment.entity.PatientEntity;
import com.appoinment.errors.ErrorService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PatientService extends UserDetailsService {

    PatientEntity getById(Long id);
    PatientEntity getByEmail(String email) throws ErrorService;
    List<PatientEntity> getAll();
    void save(String name, Long dni, String email, String password) throws ErrorService;
    void update(Long id, String name, Long dni, String email, String password) throws ErrorService;
    void delete(Long id);
    AppointmentEntity getAppointment(Long id);
    PatientEntity getEntityById(Long id);
}
