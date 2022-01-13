package com.appoinment.service;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.dto.PatientBasicDTO;
import com.appoinment.dto.PatientDTO;
import com.appoinment.entity.PatientEntity;

import java.util.List;

public interface PatientService {

    PatientDTO getById(Long id);
    PatientDTO getByEmail(String email);
    List<PatientDTO> getAll();
    PatientDTO save(PatientDTO patientDTO);
    PatientDTO update(Long id, PatientDTO patientDTO);
    void delete(Long id);

    AppointmentDTO getAppointment(Long id);
    PatientEntity getEntityById(Long id);
}
