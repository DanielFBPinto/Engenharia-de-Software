package projetoes.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projetoes.projetoes.models.Especialidade;

@Repository
public interface EspecialidadeRepo extends CrudRepository<Especialidade, Long>
{

}
