package ws2.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws2.projetoes.jsonfiles.ConsultaJSON;
import ws2.projetoes.models.Consulta;
import ws2.projetoes.models.Medico;
import ws2.projetoes.models.Paciente;
import ws2.projetoes.repositories.ConsultaRepo;
import ws2.projetoes.repositories.MedicoRepoI;
import ws2.projetoes.repositories.PacienteRepo;

@Service
public class ConsultaService {
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
        if (consultaRepo.findById(id).isPresent())
        {
            return consultaRepo.findById(id).get();
        }
        return null;
    }

    public Consulta alterarConsulta(ConsultaJSON consultaJSON)
    {
        if (this.cancelarConsulta(consultaJSON) != null)
        {
            return this.marcarConsulta(consultaJSON);
        }
        return null;
    }

    public Consulta marcarConsulta(ConsultaJSON consultaJSON)
    {
        if (pacienteRepo.findById(consultaJSON.getNomePaciente()).isPresent() && medicoRepo.findById(consultaJSON.getNomeMedico()).isPresent())
        {
            Paciente paciente = pacienteRepo.findById(consultaJSON.getNomePaciente()).get();
            Medico medico = medicoRepo.findById(consultaJSON.getNomeMedico()).get();
            if (medico.isPossible(consultaJSON.getNovaData()))
            {
                Consulta consulta = new Consulta(consultaJSON.getNovaData(), medico, paciente);
                return consultaRepo.save(consulta);
            }
        }
        return null;
    }

    public Consulta cancelarConsulta(ConsultaJSON consultaJSON)
    {
        if (pacienteRepo.findById(consultaJSON.getNomePaciente()).isPresent() && medicoRepo.findById(consultaJSON.getNomeMedico()).isPresent())
        {
            Paciente paciente = pacienteRepo.findById(consultaJSON.getNomePaciente()).get();
            Medico medico = medicoRepo.findById(consultaJSON.getNomeMedico()).get();
            Consulta fetch = medico.existeConsulta(paciente, consultaJSON.getDataAntiga());
            if (fetch != null)
            {
                medico.getConsultas().remove(fetch);
                consultaRepo.delete(fetch);
                return fetch;
            }

            //   consultaRepo.deleteById(fetch.getId());
        }
        return null;
    }

    public Consulta cancelarConsulta(Consulta consulta, Medico medico)
    {
        medico.getConsultas().remove(consulta);
        consultaRepo.delete(consulta);
        return consulta;
    }
}
