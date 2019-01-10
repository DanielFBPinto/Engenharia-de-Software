package ws2.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ws2.projetoes.models.Medico;

import java.util.Optional;

@Repository
public interface MedicoRepoI extends CrudRepository<Medico, String> {
    Optional<Medico> findById(Long id);

    Optional<Medico> findByEspecialidade(String especialidade);

    Optional<Medico> findByCedulaMedica(Integer cedulaMedica);

    Optional<Medico> findByName(String name);
}
