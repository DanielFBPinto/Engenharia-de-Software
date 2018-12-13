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
        Paciente paciente = pacienteRepo.findByName(consultaJSON.getNomePaciente()).orElse(null);
        Medico medico = medicoRepo.findByNome(consultaJSON.getNomeMedico()).orElse(null);

        Consulta fetch = medico.existeConsulta(paciente,consultaJSON.getDataAntiga());
        if(fetch == null || !medico.isPossible(consultaJSON.getDataAntiga()))
        {
            return null;
        }
        fetch.setData(consultaJSON.getNovaData());
        return consultaRepo.save(fetch);
    }
}
