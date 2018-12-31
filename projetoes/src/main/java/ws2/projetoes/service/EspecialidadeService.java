package ws2.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws2.projetoes.models.Especialidade;
import ws2.projetoes.repositories.EspecialidadeRepo;

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
