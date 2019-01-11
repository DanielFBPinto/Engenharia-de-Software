package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projetoes.projetoes.models.Administrativo;
import projetoes.projetoes.repositories.AdministrativoRepo;
import projetoes.projetoes.service.AdministrativoService;

@RestController
@RequestMapping(value = "/administrativo")
public class AdministrativoController
{
    @Autowired
    private AdministrativoService administrativoService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Administrativo>> getAllAdministrativo()
    {
        Iterable<Administrativo> allAdministrativo = administrativoService.getAllAdministrativo();
        if (allAdministrativo == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allAdministrativo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrativo> getAdministrativoById(@PathVariable("id") Long id)
    {
        Administrativo administrativo = administrativoService.findById(id);
        if (administrativo == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(administrativo);
    }
}
