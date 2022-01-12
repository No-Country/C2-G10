package com.appoinment.mapper;

import com.appoinment.dto.PatientBasicDTO;
import com.appoinment.dto.PatientDTO;
import com.appoinment.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientMapper {

    private AppointmentMapper appointmentMapper;

    @Autowired
    public PatientMapper(AppointmentMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    public PatientEntity dtoToEntity(PatientDTO dto) {
        PatientEntity entity = new PatientEntity();
        entity.setName(dto.getName());
        entity.setDni(dto.getDni());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public PatientDTO entityToDTO(PatientEntity entity) {
        PatientDTO dto = new PatientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDni(entity.getDni());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        // validar si se cargan los turnos y setear
        if (entity.getAppointment() != null) {
            dto.setAppointment(this.appointmentMapper.entityToDTO(entity.getAppointment()));
        }
        return dto;
    }

    public List<PatientBasicDTO> entityListToBasicDTOList(List<PatientEntity> entities) {
        List<PatientBasicDTO> basicDTOList = new ArrayList<>();
        PatientBasicDTO basicDTO;
        for (PatientEntity entity : entities) {
            basicDTO = new PatientBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setName(entity.getName());
            basicDTO.setDni(entity.getDni());
            basicDTOList.add(basicDTO);
        }
        return basicDTOList;
    }

    public List<PatientDTO> entityListToDTOList(List<PatientEntity> entities) {
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (PatientEntity entity : entities) {
            patientDTOList.add(this.entityToDTO(entity));
        }
    return patientDTOList;
    }

}
