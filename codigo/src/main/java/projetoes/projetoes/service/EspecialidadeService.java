package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.models.Especialidade;
import projetoes.projetoes.repositories.EspecialidadeRepo;
import java.util.HashSet;
import java.util.Set;

@Service
public class EspecialidadeService
{
    @Autowired
    private EspecialidadeRepo especialidadeRepo;

    public Iterable<Especialidade> getAllEspecialidades()
    {
        return especialidadeRepo.findAll();
    }

    public Especialidade findById(Long id)
    {
        if (especialidadeRepo.findById(id).isPresent())
        {
            return especialidadeRepo.findById(id).get();
        }
        return null;
    }
}
