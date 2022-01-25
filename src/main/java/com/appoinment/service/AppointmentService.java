package com.appoinment.service;

import com.appoinment.entity.AppointmentEntity;
import com.appoinment.errors.ErrorService;

import java.util.List;

public interface AppointmentService {
    List<AppointmentEntity> getAllAppointments();
    void save(Long idPatient, String appointmentDate, String appointmentTime) throws ErrorService;
    void update(Long idAppointment, Long idPatient, String appointmentDate, String appointmentTime) throws ErrorService;
    void delete(Long idPatient, Long idAppointment) throws ErrorService;
    AppointmentEntity getEntityById(Long idAppointment);
}
