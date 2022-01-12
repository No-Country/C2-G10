package com.appoinment.InterfaceService;

import com.appoinment.Entidades.Paciente;

import java.util.List;
import java.util.Optional;

public interface iPacienteService {
    public List<Paciente>listar();

    public Optional<Paciente>listarId(int id);

    public int guardar(Paciente p);

    public  void eliminar (int id);
}
