package com.appoinment.service.impl;

import com.appoinment.entity.AppointmentEntity;
import com.appoinment.entity.PatientEntity;
import com.appoinment.errors.ErrorService;
import com.appoinment.repository.PatientRepository;
import com.appoinment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public PatientEntity getById(Long id) {
        return patientRepository.getById(id);
    }

    @Transactional
    public PatientEntity getByEmail(String email) throws ErrorService {
        PatientEntity patientEntity = this.patientRepository.findByEmail(email);
        if (patientEntity.getEmail() == null || patientEntity.getEmail().isEmpty()) {
            throw new ErrorService("Email no encontrado");
        } else {
            return patientEntity;
        }
    }

    public List<PatientEntity> getAll() {
        return this.patientRepository.findAll();
    }

    @Transactional
    public void save(String name, Long dni, String email, String password) throws ErrorService {
        validateParams(name, dni, email, password);
        PatientEntity patient = new PatientEntity();
        patient.setName(name);
        patient.setDni(dni);
        patient.setEmail(email);
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        patient.setPassword(encryptedPassword);
        this.patientRepository.save(patient);
    }

    @Transactional
    public void update(Long id, String name, Long dni, String email, String password) throws ErrorService {
        validateParams(name, dni, email, password);
        Optional<PatientEntity> entity = this.patientRepository.findById(id);
        if (entity.isPresent()) {
            PatientEntity patient = entity.get();
            patient.setName(name);
            patient.setDni(dni);
            patient.setEmail(email);
            String encryptedPassword = new BCryptPasswordEncoder().encode(password);
            patient.setEmail(encryptedPassword);
            this.patientRepository.save(patient);
        } else {
            throw new ErrorService("Error no se encontró el usuario solicitado.");
        }
    }

    // Dara de baja al paciente mediante el SoftDelete
    public void delete(Long id) {
        this.patientRepository.deleteById(id);
    }

    public AppointmentEntity getAppointment(Long id) {
        PatientEntity patient = getById(id); // obtenemos el paciente por id
        AppointmentEntity appointmentEntity = patient.getAppointment();
        return appointmentEntity;
    }

    public PatientEntity getEntityById(Long id) {
        Optional<PatientEntity> result = this.patientRepository.findById(id);
        PatientEntity entity = result.get();
        return entity;
    }

    public void validateParams(String name, Long dni, String email, String password) throws ErrorService {
        if (name == null || name.isEmpty()) {
            throw new ErrorService("El campo Nombre no puede estar vacio.");
        }
        if (dni == null || dni <= 0 || dni.toString().isEmpty()) {
            throw new ErrorService("El campo Dni no puede estar vacio.");
        }
        if (email == null || email.isEmpty()) {
            throw new ErrorService("El campo Email no puede estar vacio.");
        }
        if (password == null || password.isEmpty()) {
            throw new ErrorService("El campo Password no puede estar vacio.");
        }
    }

    /*
        Autenticacion al logear
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PatientEntity patient = patientRepository.findByEmail(email);
        if (patient != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();  // Listado de permisos
            GrantedAuthority auth_01 = new SimpleGrantedAuthority("MODULO_TURNOS");  // creamos permisos del paciente
            GrantedAuthority auth_02 = new SimpleGrantedAuthority("MODULO_DOCTORES");
            authorities.add(auth_01);
            authorities.add(auth_02);
            User user = new User(patient.getEmail(), patient.getPassword(), authorities); //transformamos en un usuario de spring
            return user;
        } else {
            return null;
        }
    }
}
