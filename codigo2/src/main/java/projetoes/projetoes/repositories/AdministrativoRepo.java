package projetoes.projetoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projetoes.projetoes.models.Administrativo;


import java.util.Optional;

@Repository
public interface AdministrativoRepo extends CrudRepository<Administrativo, Long> {

}