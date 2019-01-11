package ws2.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws2.projetoes.models.Medico;
import ws2.projetoes.repositories.MedicoRepoI;

import java.util.HashSet;
import java.util.Set;

@Service

public class MedicoService
{
    @Autowired
    private MedicoRepoI medicoRepo;


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

}
