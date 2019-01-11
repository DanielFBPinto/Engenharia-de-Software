package ws2.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws2.projetoes.models.Administrativo;
import ws2.projetoes.repositories.AdministrativoRepo;

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
        if (administrativoRepo.findById(id).isPresent())
        {
            return administrativoRepo.findById(id).get();
        }
        return null;
    }
}
