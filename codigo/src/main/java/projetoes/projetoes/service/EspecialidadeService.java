package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.jsonfiles.EspecialidadeJSON;
import projetoes.projetoes.models.Especialidade;
import projetoes.projetoes.repositories.EspecialidadeRepo;

import java.util.HashSet;
import java.util.Set;

@Service
public class EspecialidadeService {
    @Autowired
    private EspecialidadeRepo especialidadeRepo;

    public Iterable<Especialidade> getAllEspecialidades() {
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

    public Especialidade criarEspecialidade(EspecialidadeJSON especialidadeJSON)
    {
        Especialidade especialidade = new Especialidade(especialidadeJSON.getName());
        if(especialidade == null)
        {
            return null;
        }
        return especialidadeRepo.save(especialidade);
    }

    public Especialidade eliminarEspecialidade(EspecialidadeJSON especialidadeJSON)
    {
        Especialidade especialidade = especialidadeRepo.findByName(especialidadeJSON.getName()).get();
        if(especialidade.getNomeEspecialidade().isEmpty())
        {
            return null;
        }
        especialidadeRepo.delete(especialidade);
        return especialidade;
    }
}
