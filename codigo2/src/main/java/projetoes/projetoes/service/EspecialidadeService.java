package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.jsonfiles.EspecialidadeJSON;
import projetoes.projetoes.models.Especialidade;
import projetoes.projetoes.models.Medico;
import projetoes.projetoes.repositories.ConsultaRepo;
import projetoes.projetoes.repositories.EspecialidadeRepo;
import projetoes.projetoes.repositories.HorarioRepo;
import projetoes.projetoes.repositories.MedicoRepoI;


@Service
public class EspecialidadeService {
    @Autowired
    protected EspecialidadeRepo especialidadeRepo;
    @Autowired
    MedicoService medicoService;
    @Autowired
    MedicoRepoI medicoRepoI;
    @Autowired
    ConsultaRepo consultaRepo;
    @Autowired
    HorarioRepo horarioRepo;

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
        if(!especialidadeRepo.findByNomeEspecialidade(especialidadeJSON.getName()).isPresent())
            return null;
        Especialidade especialidade = especialidadeRepo.findByNomeEspecialidade(especialidadeJSON.getName()).get();
//        if(especialidade==null)
//        {
//            return null;
//        }
        for (Medico medico:especialidade.getMedicos()
             ) {
            medicoService.removerMedico(medico);
        }
        especialidadeRepo.delete(especialidade);
        return especialidade;
    }

}
