package projetoes.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projetoes.projetoes.models.Paciente;
@Repository
public interface PacienteRepo extends CrudRepository<Paciente,Long> {
}
