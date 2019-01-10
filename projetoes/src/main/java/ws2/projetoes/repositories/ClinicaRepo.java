package ws2.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ws2.projetoes.models.Clinica;

@Repository
public interface ClinicaRepo extends CrudRepository<Clinica, Long> {

}