package ws2.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws2.projetoes.models.Clinica;
import ws2.projetoes.repositories.ClinicaRepo;

@Service
public class ClinicaService
{
    @Autowired
    ClinicaRepo clinicaRepo;

    public Iterable<Clinica> getAllClinicas()
    {
        return clinicaRepo.findAll();
    }

    public Clinica findById(Long id)
    {
        if (clinicaRepo.findById(id).isPresent())
        {
            return clinicaRepo.findById(id).get();
        }
        return null;
    }
    public Clinica saveClinica(Clinica clinica)
    {
        return clinicaRepo.save(clinica);
    }
}