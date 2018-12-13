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
        return medicoRepo.findById(id).orElse(null);
    }

    public Medico findByEspecialidade(String especialidade)
    {
        return medicoRepo.findByEspecialidade(especialidade).orElse(null);
    }

    public Medico findByCedulaMedica(Integer cedulaMedica)
    {
        return medicoRepo.findByCedulaMedica(cedulaMedica).orElse(null);
    }
}
