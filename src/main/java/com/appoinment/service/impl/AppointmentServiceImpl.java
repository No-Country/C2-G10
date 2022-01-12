package com.appoinment.service.impl;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.entity.AppointmentEntity;
import com.appoinment.mapper.AppointmentMapper;
import com.appoinment.repository.AppointmentRepository;
import com.appoinment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        List<AppointmentEntity> entities = this.appointmentRepository.findAll();
        List<AppointmentDTO> dtoList = this.appointmentMapper.entityListToDTOList(entities);
        return dtoList;
    }

    @Override
    public AppointmentDTO save(AppointmentDTO dto) {
        AppointmentEntity entity = this.appointmentMapper.dtoToEntity(dto);
        AppointmentEntity entitySaved = this.appointmentRepository.save(entity);
        AppointmentDTO resultDTO = this.appointmentMapper.entityToDTO(entitySaved);
        return resultDTO;
    }

    @Override
    public AppointmentEntity getEntityById(Long id) {
        Optional<AppointmentEntity> result = this.appointmentRepository.findById(id);
        AppointmentEntity entity = result.get();
        return entity;
    }
}
