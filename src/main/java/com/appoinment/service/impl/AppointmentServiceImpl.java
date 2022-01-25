package com.appoinment.service.impl;

import com.appoinment.entity.AppointmentEntity;
import com.appoinment.errors.ErrorService;
import com.appoinment.mapper.AppointmentMapper;
import com.appoinment.repository.AppointmentRepository;
import com.appoinment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientServiceImpl patientService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, PatientServiceImpl patientService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.patientService = patientService;
    }

    @Override
    public List<AppointmentEntity> getAllAppointments() {
       return this.appointmentRepository.findAll();
    }

    @Override
    public AppointmentEntity getEntityById(Long id) {
        return this.appointmentRepository.getById(id);
    }

    @Override
    public void save(Long idPatient, String appointmentDate, String appointmentTime) throws ErrorService {
        validateParams(idPatient, appointmentDate, appointmentTime);
        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setAppointmentDate(this.string2LocalDate(appointmentDate));
        appointment.setAppointmentTime(this.string2LocalTime(appointmentTime));
        appointment.setPatient(this.patientService.getById(idPatient));
        this.appointmentRepository.save(appointment);
    }

    @Transactional
    public void update(Long idAppointment, Long idPatient, String appointmentDate, String appointmentTime) throws ErrorService {
        validateParams(idPatient, appointmentDate, appointmentTime);
        Optional<AppointmentEntity> result = this.appointmentRepository.findById(idAppointment);
        if (result.isPresent()) {
            AppointmentEntity appointment = result.get();
            if (appointment.getPatient().getId().equals(idPatient)) {
                appointment.setAppointmentDate(this.string2LocalDate(appointmentDate));
                appointment.setAppointmentTime(this.string2LocalTime(appointmentTime));
                this.appointmentRepository.save(appointment);
            } else {
                throw new ErrorService("Error, no se encontró el paciente con el turno asociado.");
            }
        } else {
            throw new ErrorService("Erro, no se encontró el turno solicitado.");
        }
    }

    @Transactional
    public void delete(Long idPatient, Long idAppointment) throws ErrorService {
        Optional<AppointmentEntity> result = this.appointmentRepository.findById(idAppointment);
        if (result.isPresent()) {
            AppointmentEntity appointment = result.get();
            if (appointment.getPatient().getId().equals(idPatient)) {
                this.appointmentRepository.deleteById(idAppointment);
            }
        } else {
            throw new ErrorService("No se encontró el turno solicitado");
        }
    }

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

    private void validateParams(Long idPatient, String appointmentDate, String appointmentTime) throws ErrorService {
        if (idPatient == null || idPatient <= 0 || idPatient.toString().isEmpty()) {
            throw new ErrorService("No se encontró el paciente asociado al turno.");
        }
        if (appointmentDate == null || appointmentDate.toString().isEmpty()) {
            throw new ErrorService("El campo Fecha no puede estar vacio.");
        }
        if (appointmentTime == null || appointmentTime.isEmpty()) {
            throw new ErrorService("El campo Horario no puede estar vacio.");
        }
    }
}
