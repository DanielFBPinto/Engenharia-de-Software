package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.repositories.HorarioRepo;

@Service
public class HorarioService
{
    @Autowired
    private HorarioRepo horarioRepo;

    public Iterable<Horario> getAllHorarios()
    {
        return horarioRepo.findAll();
    }

    public Horario findById(long id)
    {
        return horarioRepo.findById(id).orElse(null);
    }
}
