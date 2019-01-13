package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.jsonfiles.PacienteJSON;
import projetoes.projetoes.models.Paciente;
import projetoes.projetoes.repositories.PacienteRepo;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepo pacienteRepo;

    public Iterable<Paciente> getAllPacientes() {
        return pacienteRepo.findAll();
    }
    public Paciente registar(PacienteJSON pacienteJSON){

        Paciente paciente= new Paciente(pacienteJSON.getName(),pacienteJSON.getDataNascimento(),pacienteJSON.getNumCC());
        if(paciente!=null)
            return pacienteRepo.save(paciente);

        return null;
    }
    public Paciente alterarPaciente(PacienteJSON pacienteJSON){
        if(!pacienteRepo.findById(pacienteJSON.getMyid()).isPresent())
            return null;
      Paciente  paciente =pacienteRepo.findById(pacienteJSON.getMyid()).get();

        paciente.setName(pacienteJSON.getName());
        paciente.setDataNascimento(pacienteJSON.getDataNascimento());
        paciente.setNumCC(pacienteJSON.getNumCC());
       // pacienteRepo.save(paciente);
        return pacienteRepo.save(paciente);
    }
}
