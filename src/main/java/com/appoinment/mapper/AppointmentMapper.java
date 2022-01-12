package com.appoinment.mapper;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.entity.AppointmentEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentMapper {

    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    /*
    private LocalTime string2LocalTime(String stringTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(stringTime, formatter);
        return time;
    }
     */

    public AppointmentEntity dtoToEntity(AppointmentDTO dto) {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setAppointmentDate(this.string2LocalDate(dto.getAppointmentDate()));
        return entity;
    }

    public AppointmentDTO entityToDTO(AppointmentEntity entity) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(entity.getId());
        dto.setAppointmentDate(entity.getAppointmentDate().toString());
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
