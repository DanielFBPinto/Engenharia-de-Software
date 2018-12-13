package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.models.Paciente;
import projetoes.projetoes.repositories.PacienteRepo;

@Service
public class PacienteService
{
    @Autowired
    private PacienteRepo pacienteRepo;

    public Iterable<Paciente> getAllPacientes()
    {
        return pacienteRepo.findAll();
    }
}
