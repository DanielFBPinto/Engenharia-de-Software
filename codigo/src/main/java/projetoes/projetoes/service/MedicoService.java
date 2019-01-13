package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.filters.FilterMedicoObject;
import projetoes.projetoes.filters.medicoFilters.MedicoFilterService;
import projetoes.projetoes.jsonfiles.MedicoJSON;
import projetoes.projetoes.models.Consulta;
import projetoes.projetoes.models.Especialidade;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;
import projetoes.projetoes.repositories.EspecialidadeRepo;
import projetoes.projetoes.repositories.MedicoRepoI;
import java.util.HashSet;
import java.util.Set;

@Service
public class MedicoService
{
    @Autowired
    private MedicoRepoI medicoRepo;
    @Autowired
    private EspecialidadeRepo especialidadeRepo;
    @Autowired
    private MedicoFilterService medicoFilterService;
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private HorarioService horarioService;

    public Set<Medico> getAllMedicos()
    {
        Set<Medico> medicos = new HashSet<>();
        for (Medico medico : medicoRepo.findAll())
        {
            medicos.add(medico);
        }
        return medicos;
    }

    public Medico findById(Long id)
    {
        if (medicoRepo.findById(id).isPresent())
        {
            return medicoRepo.findById(id).get();
        }
        return null;
    }

    public Medico findByEspecialidade(String especialidade)
    {
        if (medicoRepo.findByEspecialidade(especialidade).isPresent())
        {
            return medicoRepo.findByEspecialidade(especialidade).get();
        }
        return null;
    }

    public Medico findByCedulaMedica(Integer cedulaMedica)
    {
        if (medicoRepo.findByCedulaMedica(cedulaMedica).isPresent())
        {
            return medicoRepo.findByCedulaMedica(cedulaMedica).get();
        }
        return null;
    }

    public Set<Medico> getFilteredMedicos(FilterMedicoObject filterMedicoObject)
    {
        return medicoFilterService.filterMedicos(getAllMedicos(), filterMedicoObject);
    }

    public Medico criarMedico(MedicoJSON medicoJSON)
    {
        Especialidade especialidade = especialidadeRepo.findByNomeEspecialidade(medicoJSON.getEspecialidade()).get();
        if(especialidade==null){
            especialidade = new Especialidade(medicoJSON.getEspecialidade());
            if(especialidade == null)
            {
                return null;
            }
        }

        Medico medico = new Medico(medicoJSON.getNome(),medicoJSON.getDataNascimento(),medicoJSON.getNumCC(),medicoJSON.getCedulaMedica(),especialidade);
        if(medico == null)
        {
            return null;
        }
        return medicoRepo.save(medico);

    }

    public Medico alterarMedico(MedicoJSON medicoJSON)
    {
        if(!medicoRepo.findById(medicoJSON.getMyid()).isPresent())
            return null;
     Medico medico= medicoRepo.findById(medicoJSON.getMyid()).get();

     Especialidade especialidade;
        if(!especialidadeRepo.findByNomeEspecialidade(medicoJSON.getEspecialidade()).isPresent()){

             especialidade = new Especialidade(medicoJSON.getEspecialidade());
        }
           else{
             especialidade = especialidadeRepo.findByNomeEspecialidade(medicoJSON.getEspecialidade()).get();

            if(especialidade!=medico.getEspecialidade()){
                for (Consulta consulta:medico.getConsultas()
                        ) {
                    consultaService.cancelarConsulta(consulta,medico);
                }
            }
        }

        medico.setEspecialidade(especialidade);
        medico.setName(medicoJSON.getNome());
        medico.setCedulaMedica(medicoJSON.getCedulaMedica());
        medico.setNumCC(medicoJSON.getNumCC());
        medico.setDataNascimento(medicoJSON.getDataNascimento());

        return medicoRepo.save(medico);
    }

    public Medico removerMedico(MedicoJSON medicoJSON)
    {
        if(!medicoRepo.findById(medicoJSON.getMyid()).isPresent())
            return null;
        Medico medico= medicoRepo.findById(medicoJSON.getMyid()).get();

        for (Horario horario:medico.getHorarios()
             ) {
            horarioService.cancelarHorarioRIPconsultas(horario,medico);
        }
        medicoRepo.delete(medico);
        return medico;
    }
    public Medico removerMedico(Medico medico)
    {
        for (Horario horario:medico.getHorarios()
                ) {
            horarioService.cancelarHorarioRIPconsultas(horario,medico);
        }
        medicoRepo.delete(medico);
        return medico;
    }
}
