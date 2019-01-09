package ws2.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ws2.projetoes.models.Paciente;

import java.util.Optional;

@Repository
public interface PacienteRepo extends CrudRepository<Paciente, Long> {
    Optional<Paciente> findByName(String name);
}
