package ws2.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ws2.projetoes.models.Administrativo;

@Repository
public interface AdministrativoRepo extends CrudRepository<Administrativo, Long> {

}