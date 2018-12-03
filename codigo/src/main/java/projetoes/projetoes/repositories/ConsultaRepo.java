package projetoes.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projetoes.projetoes.models.Consulta;

@Repository
public interface ConsultaRepo extends CrudRepository<Consulta,Long> {

}
