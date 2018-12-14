package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.jsonfiles.ConsultaJSON;
import projetoes.projetoes.models.Consulta;
import projetoes.projetoes.models.Medico;
import projetoes.projetoes.models.Paciente;
import projetoes.projetoes.repositories.ConsultaRepo;
import projetoes.projetoes.repositories.MedicoRepoI;
import projetoes.projetoes.repositories.PacienteRepo;

import java.util.Optional;

@Service
public class ConsultaService
{
    @Autowired
    private ConsultaRepo consultaRepo;
    @Autowired
    private PacienteRepo pacienteRepo;
    @Autowired
    private MedicoRepoI medicoRepo;

    public Iterable<Consulta> getAllConsultas()
    {
        return consultaRepo.findAll();
    }

    public Consulta findById(Long id)
    {
        return consultaRepo.findById(id).orElse(null);
    }

    public Consulta alterarConsulta(ConsultaJSON consultaJSON)
    {
        if(pacienteRepo.findByName(consultaJSON.getNomePaciente()).isPresent() && medicoRepo.findByNome(consultaJSON.getNomeMedico()).isPresent())
        {
            Paciente paciente = pacienteRepo.findByName(consultaJSON.getNomePaciente()).get();
            Medico medico = medicoRepo.findByNome(consultaJSON.getNomeMedico()).get();

            Consulta fetch = medico.existeConsulta(paciente,consultaJSON.getDataAntiga());
            if(fetch == null || !medico.isPossible(consultaJSON.getDataAntiga()))
            {
                return null;
            }
            fetch.setData(consultaJSON.getNovaData());
            return consultaRepo.save(fetch);
        }
        return null;
    }

    public Consulta marcarConsulta(ConsultaJSON consultaJSON)
    {

        if(pacienteRepo.findByName(consultaJSON.getNomePaciente()).isPresent() && medicoRepo.findByNome(consultaJSON.getNomeMedico()).isPresent())
        {
            Paciente paciente = pacienteRepo.findByName(consultaJSON.getNomePaciente()).get();
            Medico medico = medicoRepo.findByNome(consultaJSON.getNomeMedico()).get();

            if(medico.isPossible(consultaJSON.getNovaData()))
            {
                Consulta consulta = new Consulta(consultaJSON.getNovaData(),medico,paciente);
                return consultaRepo.save(consulta);
            }
        }
        return null;
    }

    public Consulta cancelarConsulta(ConsultaJSON consultaJSON)
    {
        if(pacienteRepo.findByName(consultaJSON.getNomePaciente()).isPresent() && medicoRepo.findByNome(consultaJSON.getNomeMedico()).isPresent())
        {
            Paciente paciente = pacienteRepo.findByName(consultaJSON.getNomePaciente()).get();
            Medico medico = medicoRepo.findByNome(consultaJSON.getNomeMedico()).get();

            Consulta fetch = medico.existeConsulta(paciente,consultaJSON.getDataAntiga());
            medico.getMyConsulta().remove(fetch);
            consultaRepo.delete(fetch);
        }
        return null;
    }
}
