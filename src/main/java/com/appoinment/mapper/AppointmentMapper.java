package com.appoinment.mapper;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.entity.AppointmentEntity;
import com.appoinment.service.PatientService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentMapper {

    private PatientService patientService;

    public AppointmentMapper(@Lazy PatientService patientService) {
        this.patientService = patientService;
    }

    // Formatea un string a LocalDate: Usado en el mapeo de DTO a Entity
    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    private LocalTime string2LocalTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime resultTime = LocalTime.parse(time, formatter);
        return resultTime;
    }

    public AppointmentEntity dtoToEntity(AppointmentDTO dto) {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setAppointmentDate(this.string2LocalDate(dto.getAppointmentDate()));
        entity.setAppointmentTime(this.string2LocalTime(dto.getAppointmentTime()));
        entity.setPatient(this.patientService.getEntityById(dto.getIdPatient()));
        return entity;
    }

    public AppointmentDTO entityToDTO(AppointmentEntity entity) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(entity.getId());
        dto.setAppointmentDate(entity.getAppointmentDate().toString());
        dto.setAppointmentTime(entity.getAppointmentTime().toString());
        dto.setIdPatient(entity.getPatient().getId());
        return dto;
    }

    public List<AppointmentDTO> entityListToDTOList(List<AppointmentEntity> entities) {
        List<AppointmentDTO> dtoList = new ArrayList<>();
        for (AppointmentEntity entity : entities) {
            dtoList.add(this.entityToDTO(entity));
        }
        return dtoList;
    }
}
