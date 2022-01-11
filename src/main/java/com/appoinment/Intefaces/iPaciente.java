package com.appoinment.Intefaces;

import com.appoinment.Entidades.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iPaciente extends CrudRepository<Paciente, Integer> {
}
