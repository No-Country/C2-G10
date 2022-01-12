package com.appoinment.service;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.entity.AppointmentEntity;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO save(AppointmentDTO dto);
    AppointmentEntity getEntityById(Long idAppointment);
}
