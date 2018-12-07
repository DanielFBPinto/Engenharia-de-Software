package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import projetoes.projetoes.models.Administrativo;
import projetoes.projetoes.repositories.AdministrativoRepo;

@Controller
@RequestMapping(value = "/Administrativo")
public class AdministrativoController {
    @Autowired
    AdministrativoRepo administrativoRepo;

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Administrativo> getAllAdministrativo(){
        return administrativoRepo.findAll();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Administrativo getAdministrativoById(@PathVariable("id")Long id) {
        return administrativoRepo.findById(id).get();
    }
}
