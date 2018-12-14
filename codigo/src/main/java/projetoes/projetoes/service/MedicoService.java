package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.models.Medico;
import projetoes.projetoes.repositories.MedicoRepoI;

@Service
public class MedicoService
{
    @Autowired
    private MedicoRepoI medicoRepo;

    public Iterable<Medico> getAllMedicos()
    {
        return medicoRepo.findAll();
    }

    public Medico findById(Long id)
    {
        if(medicoRepo.findById(id).isPresent())
        {
            return medicoRepo.findById(id).get();
        }
        return null;
    }

    public Medico findByEspecialidade(String especialidade)
    {
        if(medicoRepo.findByEspecialidade(especialidade).isPresent())
        {
            return medicoRepo.findByEspecialidade(especialidade).get();
        }
        return null;
    }

    public Medico findByCedulaMedica(Integer cedulaMedica)
    {
        if(medicoRepo.findByCedulaMedica(cedulaMedica).isPresent())
        {
            return medicoRepo.findByCedulaMedica(cedulaMedica).get();
        }
        return null;
    }
}
