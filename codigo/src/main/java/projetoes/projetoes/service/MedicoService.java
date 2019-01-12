package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.filters.FilterMedicoObject;
import projetoes.projetoes.filters.medicoFilters.MedicoFilter;
import projetoes.projetoes.filters.medicoFilters.MedicoFilterService;
import projetoes.projetoes.jsonfiles.MedicoJSON;
import projetoes.projetoes.models.Medico;
import projetoes.projetoes.repositories.MedicoRepoI;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MedicoService
{
    @Autowired
    private MedicoRepoI medicoRepo;
    @Autowired
    private MedicoFilterService medicoFilterService;

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
        Medico medico = new Medico(medicoJSON.getNome(),medicoJSON.getNumCC(),medicoJSON.getMyid());
        if(medico == null)
        {
            return null;
        }
        return medicoRepo.save(medico);
    }

    public Medico alterarMedico(MedicoJSON medicoJSON)
    {
        Medico medico = new Medico()
    }

    public Medico removerMedico(MedicoJSON medicoJSON)
    {
        Medico medico = medicoRepo.findById(medicoJSON.getMyid());
        if(medico == null)
        {
            return null;
        }
        medicoRepo.delete(medico);
        return medico;
    }
}
