package projetoes.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoes.projetoes.models.Administrativo;
import projetoes.projetoes.repositories.AdministrativoRepo;

@Service
public class AdministrativoService
{
    @Autowired
    AdministrativoRepo administrativoRepo;

    public Iterable<Administrativo> getAllAdministrativo()
    {
        return administrativoRepo.findAll();
    }

    public Administrativo findById(Long id)
    {
        return administrativoRepo.findById(id).orElse(null);
    }
}
