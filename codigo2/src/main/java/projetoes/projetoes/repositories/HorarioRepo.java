package projetoes.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projetoes.projetoes.models.Horario;

@Repository
public interface HorarioRepo extends CrudRepository<Horario, Long> {

}
