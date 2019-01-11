package ws2.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws2.projetoes.models.Paciente;
import ws2.projetoes.repositories.PacienteRepo;

@Service
public class PacienteService
{
    @Autowired
    private PacienteRepo pacienteRepo;

    public Iterable<Paciente> getAllPacientes()
    {
        return pacienteRepo.findAll();
    }

    public Paciente findById(Long id)
    {
        if(pacienteRepo.findById(id).isPresent())
        {
            return pacienteRepo.findById(id).get();
        }
        return null;
    }
}
