package projetoes.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projetoes.projetoes.models.Clinica.Medico;

import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface MedicoRepoI extends CrudRepository<Medico,String>
{
    Optional<Medico> findByEspecialidade(String especialidade);
    Optional<Medico> findByCedulaMedica(Integer cedulaMedica);
}
