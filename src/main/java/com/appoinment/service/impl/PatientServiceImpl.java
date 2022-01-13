package com.appoinment.service.impl;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.dto.PatientDTO;
import com.appoinment.entity.AppointmentEntity;
import com.appoinment.entity.PatientEntity;
import com.appoinment.mapper.PatientMapper;
import com.appoinment.repository.AppointmentRepository;
import com.appoinment.repository.PatientRepository;
import com.appoinment.service.AppointmentService;
import com.appoinment.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    // repo
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    // mapper
    private final PatientMapper patientMapper;
    // service
    private final AppointmentService appointmentService;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, AppointmentService appointmentService, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
    }

    public PatientDTO getById(Long id) {
        Optional<PatientEntity> patientEntity = this.patientRepository.findById(id);
        if (patientEntity.isEmpty()) {
            log.info("Id patient not valid"); // error visible en log
            //throw New ParamNotFound("id patient not valid");
        }
        PatientDTO patientDTO = this.patientMapper.entityToDTO(patientEntity.get());
        return patientDTO;
    }

    public PatientDTO getByEmail(String email) {
        PatientEntity patientEntity = this.patientRepository.findByEmail(email);
        if (patientEntity.getEmail() == null || patientEntity.getEmail().isEmpty()) {
            log.info("Email patient not found");
            // throw exception
        }
        PatientDTO patientDTO = this.patientMapper.entityToDTO(patientEntity);
        return patientDTO;
    }

    public List<PatientDTO> getAll() {
        List<PatientEntity> entities = this.patientRepository.findAll();
        List<PatientDTO> patientDTOList = this.patientMapper.entityListToDTOList(entities);
        return patientDTOList;
    }

    public PatientDTO save(PatientDTO patientDTO) {
        PatientEntity entity = this.patientMapper.dtoToEntity(patientDTO);
        PatientEntity entitySaved = this.patientRepository.save(entity);
        PatientDTO resultDTO = this.patientMapper.entityToDTO(entitySaved);
        return resultDTO;
    }

    @Override
    public PatientDTO update(Long id, PatientDTO patientDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    public AppointmentDTO getAppointment(Long id) {
        PatientDTO entity = getById(id); // obtenemos el paciente por id
        AppointmentDTO appointmentDTO = entity.getAppointment();
        return appointmentDTO;
    }

    public PatientEntity getEntityById(Long id) {
        Optional<PatientEntity> result = this.patientRepository.findById(id);
        PatientEntity entity = result.get();
        return entity;
    }
}
