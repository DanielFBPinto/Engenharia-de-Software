package ws2.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ws2.projetoes.models.Horario;

@Repository
public interface HorarioRepo extends CrudRepository<Horario, Long> {

}
