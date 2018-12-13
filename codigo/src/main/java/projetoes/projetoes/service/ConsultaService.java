package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    //oioioi
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

    public Consulta alterarConsulta(Consulta consulta)
    {
        Paciente paciente = pacienteRepo.findByName(consulta.getMyPaciente().getName()).orElse(null);
        Medico medico = medicoRepo.findByNome(consulta.getMyMedico().getName()).orElse(null);

        Consulta fetch = medico.existeConsulta(paciente,consulta.getData());
        if(fetch == null || !medico.isPossible(consulta.getData()))
        {
            return null;
        }
        fetch.setData(consulta.getData());
        return consultaRepo.save(fetch);
    }
}
