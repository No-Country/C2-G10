package com.appoinment.Services;

import com.appoinment.Entidades.Paciente;
import com.appoinment.Intefaces.iPaciente;
import com.appoinment.InterfaceService.iPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServices implements iPacienteService {

    @Autowired
    private iPaciente data;

    @Override
    public List<Paciente> listar() {
        return (List<Paciente>) data.findAll();
    }

    @Override
    public Optional<Paciente> listarId(int id) {

        return Optional.empty();
    }

    @Override
    public int guardar(Paciente p) {
        int res=0;
        Paciente paciente = data.save(p);

        if(paciente.equals(null)){
            res=1;

        }
        return res;
    }


    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
